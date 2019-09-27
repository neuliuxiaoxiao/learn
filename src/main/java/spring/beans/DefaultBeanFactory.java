package spring.beans;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.CollectionUtils;

import java.io.Closeable;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Title DefaultBeanFactory
 * @Description bean����BeanDefinition����, bean����ע��BeanDefinitionRegistryҲ����,
 * �Ϳ���ʵ��һ��Ĭ�ϵ�bean����DefaultBeanFactory������beanʵ����,DefaultBeanFactory������Ҫʵ��BeanFactory��,
 * ����Ҫʵ��Bean����ע��ӿ�BeanDefinitionRegistry,��ΪҪ��bean����ע�ᵽbean��������
 * @Author liuxi58
 * @Date 2019/9/27 11:24
 **/
public class DefaultBeanFactory implements BeanFactory, BeanDefinitionRegistry, Closeable {

    private final Log logger = LogFactory.getLog(getClass());

    //��Map�����bean������Ϣ
    private Map<String, BeanDefinition> beanDefintionMap = new ConcurrentHashMap<>(256);

    //��Map����Ŵ�����beanʵ��,ע������ֻ�Ǵ�ŵ���bean,��ʵ��ÿ�ζ�Ҫ�����µ�,����Ҫ���
    private Map<String, Object> beanMap = new ConcurrentHashMap<>(256);

    //��¼���ڹ����bean
    private ThreadLocal<Set<String>> buildingBeans = new ThreadLocal<>();

    private List<BeanPostProcessor> beanPostProcessors = Collections.synchronizedList(new ArrayList<>());
    //ע��bean����
    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition)
            throws BeanDefinitionRegistException {
        //�жϸ����beanName��beanDefinition����Ϊ��
        Objects.requireNonNull(beanName, "ע��bean��Ҫ����beanName");
        Objects.requireNonNull(beanDefinition, "ע��bean��Ҫ����beanDefinition");

        // У������bean�Ƿ�Ϸ�
        if (!beanDefinition.validate()) {
            throw new BeanDefinitionRegistException("����Ϊ[" + beanName + "] ��bean���岻�Ϸ���" + beanDefinition);
        }

        //����Ѵ���bean��������쳣
        if (this.containsBeanDefinition(beanName)) {
            throw new BeanDefinitionRegistException(
                    "����Ϊ[" + beanName + "] ��bean�����Ѵ���:" + this.getBeanDefinition(beanName));
        }

        //��bean����ŵ�Map����
        this.beanDefintionMap.put(beanName, beanDefinition);
    }

    //����bean�����ִ�Map�����ȡbean����
    @Override
    public BeanDefinition getBeanDefinition(String beanName) {
        return this.beanDefintionMap.get(beanName);
    }

    //����bean�������ж�Map�����Ƿ����bean����
    @Override
    public boolean containsBeanDefinition(String beanName) {

        return this.beanDefintionMap.containsKey(beanName);
    }

    //����bean�����ֻ�ȡbeanʵ��,������Ҫ���Ĺ����Ǵ���beanʵ���Ͷ�beanʵ�����г�ʼ��
    @Override
    public Object getBean(String name) throws Exception {
        return this.doGetBean(name);
    }

    @Override
    public void resiterBeanPostProcessor(BeanPostProcessor bpp) {
        this.beanPostProcessors.add(bpp);
        if (bpp instanceof BeanFactoryAware){
            ((BeanFactoryAware) bpp).setBeanFactory(this);
        }
    }

    //����bean�����ֻ�ȡbeanʵ��,������Ҫ���Ĺ����Ǵ���beanʵ���Ͷ�beanʵ�����г�ʼ��
    protected Object doGetBean(String beanName) throws Exception {
        //�жϸ����bean���ֲ���Ϊ��
        Objects.requireNonNull(beanName, "beanName����Ϊ��");

        //�ȴ�beanMap�����ȡbeanʵ��
        Object instance = beanMap.get(beanName);

        //���beanMap�����Ѵ���beanʵ����ֱ�ӷ���,����Ҫ�ߺ����������
        if (instance != null) {
            return instance;
        }
        //��beanDefinitionMap�����ȡbean������Ϣ
        BeanDefinition bd = this.getBeanDefinition(beanName);
        //bean������Ϣ����Ϊ��
        Objects.requireNonNull(bd, "beanDefinition����Ϊ��");
        // ��¼���ڴ�����Bean
        Set<String> ingBeans = this.buildingBeans.get();
        if (ingBeans == null) {
            ingBeans = new HashSet<>();
            this.buildingBeans.set(ingBeans);
        }
        // ���ѭ������ ��Ϊ�����bean���ڹ��죬��ʱ������������bean����������ʱ����û�д����þ���Ҫ���쳣��
        if (ingBeans.contains(beanName)) {
            throw new Exception(beanName + " ѭ��������" + ingBeans);
        }
        // ��¼���ڴ�����Bean
        ingBeans.add(beanName);
        //��ȡbean������
        Class<?> cl = bd.getBeanClass();
        if (cl != null) {
            //���bean�����Ͳ�Ϊ��,���ҹ���������Ϊ�� ˵��ʹ�ù��캯���ķ�ʽ������beanʵ��
            if (StringUtils.isBlank(bd.getFactoryMethodName())) {
                //���췽�����������
                instance = this.createInstanceByConstructor(bd);
            }
            //���bean�����Ͳ�Ϊ��,���ҹ�����������Ϊ��,˵����ʹ�þ�̬���������ķ�ʽ������beanʵ��
            else {
                //��̬��������
                instance = this.createInstanceByStaticFactoryMethod(bd);
            }
        }
        //���bean������Ϊ��,˵��ʹ�ù���bean�ķ�ʽ������beanʵ��
        else {
            instance = this.createInstanceByFactoryBean(bd);
        }
        // ������ʵ�����Ƴ������м�¼
        ingBeans.remove(beanName);
        // ������������
        this.setPropertyDIValues(bd, instance);
        //ִ�г�ʼ��
        this.doInit(bd, instance);
        //��ŵ�����bean��beanMap
        if (bd.isSingleton()) {
            beanMap.put(beanName, instance);
        }
        return instance;
    }

    // ���췽����������� ����
    private Object createInstanceByConstructor(BeanDefinition bd)
            throws Exception {
        try {
            //��ȡbean�����еĹ������
            Object[] args = this.getConstructorArgumentValues(bd);
            //����������Ϊ�վ�ʹ���޲ι��캯��
            if (args == null) {
                return bd.getBeanClass().newInstance();
            } else {
                //�����вι��캯��������
                return this.determineConstructor(bd, args).newInstance(args);
            }
        } catch (SecurityException e1) {
            logger.error("����bean��ʵ���쳣,beanDefinition��" + bd, e1);
            throw e1;
        }
    }

    // ��̬�������� ����
    private Object createInstanceByStaticFactoryMethod(BeanDefinition bd) throws Exception {
        //�õ�bean������
        Class<?> type = bd.getBeanClass();
        Object[] realArgs = this.getRealValues(bd.getConstructorArgumentValues());
        Method m = this.determineFactoryMethod(bd, realArgs, null);
        return m.invoke(type, realArgs);
    }

    // ����bean��ʽ��������� ����
    private Object createInstanceByFactoryBean(BeanDefinition bd) throws Exception {

        //ͨ��bean������Ϣ�й���bean�����ֻ�ȡ����bean��ʵ��
        Object factoryBean = this.doGetBean(bd.getFactoryBeanName());
        Object[] realArgs = this.getRealValues(bd.getConstructorArgumentValues());
        Method m = this.determineFactoryMethod(bd, realArgs, factoryBean.getClass());
        return m.invoke(factoryBean, realArgs);
    }

    //ִ�г�ʼ������
    private void doInit(BeanDefinition bd, Object instance) throws Exception {
        // ��ȡbean�����еĳ�ʼ������,������ڳ�ʼ��������ͨ������ȥִ�г�ʼ������
        if (StringUtils.isNotBlank(bd.getInitMethodName())) {
            Method m = instance.getClass().getMethod(bd.getInitMethodName(), null);
            m.invoke(instance, null);
        }
    }

    //����
    @Override
    public void close() throws IOException {
        // ִ�е���ʵ�������ٷ���
        for (Map.Entry<String, BeanDefinition> e : this.beanDefintionMap.entrySet()) {
            String beanName = e.getKey();
            BeanDefinition bd = e.getValue();

            //��ȡbean�����е����ٷ���,����������ٷ�����ͨ������ȥִ�����ٷ���
            if (bd.isSingleton() && StringUtils.isNotBlank(bd.getDestoryMethodName())) {
                Object instance = this.beanMap.get(beanName);
                try {
                    Method m = instance.getClass().getMethod(bd.getDestoryMethodName(), null);
                    m.invoke(instance, null);
                } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
                        | InvocationTargetException e1) {
                    logger.error("ִ��bean[" + beanName + "] " + bd + " �� ���ٷ����쳣��", e1);
                }
            }
        }
    }

    //��bean�����еĹ����������תΪ��ʵ��ֵ
    private Object[] getConstructorArgumentValues(BeanDefinition bd) throws Exception {
        return this.getRealValues(bd.getConstructorArgumentValues());
    }

    //��bean�����еĹ����������תΪ��ʵ��ֵ
    private Object[] getRealValues(List<?> constructorArgumentValues) throws Exception {
        if (CollectionUtils.isEmpty(constructorArgumentValues)) {
            return null;
        }
        Object[] values = new Object[constructorArgumentValues.size()];
        int i = 0;
        Object v = null;
        for (Object rv : constructorArgumentValues) {
            if (rv == null) {
                v = null;
            } else if (rv instanceof BeanReference) {
                v = this.doGetBean(((BeanReference) rv).getBeanName());
            } else if (rv instanceof Object[]) {
//                v = rv;
            } else if (rv instanceof Collection) {

            } else if (rv instanceof Properties) {

            } else if (rv instanceof Map) {

            } else {
                v = rv;
            }
            values[i++] = v;
        }
        return values;
    }

    //���ҹ��췽���ķ���
    private Constructor<?> determineConstructor(BeanDefinition bd, Object[] args) throws Exception {
        Constructor<?> ct = null;
        //�������Ϊ��,�����޲ι��췽��
        if (args == null) {
            return bd.getBeanClass().getConstructor(null);
        }
        //����ԭ��bean,�ӵڶ��ο�ʼ��ȡbeanʵ��ʱ,��ֱ�ӻ�õ�һ�λ���Ĺ��췽��
        ct = bd.getConstructor();
        if (ct != null) {
            return ct;
        }
        //�ȸ��ݲ������ͻ�ȡ��ȷƥ��Ĺ��췽��
        Class<?>[] paramTypes = new Class[args.length];
        int j = 0;
        for (Object p : args) {
            paramTypes[j++] = p.getClass();
        }
        try {
            ct = bd.getBeanClass().getConstructor(paramTypes);
        } catch (Exception e) {
        }
        //������ݲ������ͽ��о�ȷ���β���û���ҵ����췽��,�ͻ�����еĹ��췽������,ͨ��������������,�ٶԱ��β�������ʵ������
        if (ct == null) {
            //û�о�ȷ��������ƥ���,�����ƥ�����еĹ��췽��
            //�ж��߼������жϲ�������,�����αȶ��β�������ʵ������
            outer:
            for (Constructor<?> ct0 : bd.getBeanClass().getConstructors()) {
                Class<?>[] paramterTypes = ct0.getParameterTypes();
                if (paramterTypes.length == args.length) {
                    for (int i = 0; i < paramterTypes.length; i++) {
                        //isAssignableFrom������ʾ�Ƿ���԰�args[i].getClass()��ֵ��paramterTypes[i]
                        if (!paramterTypes[i].isAssignableFrom(args[i].getClass())) {
                            continue outer;
                        }
                    }
                    ct = ct0;
                    break outer;
                }
            }
        }
        //����ҵ��˹��췽��,������ԭ�͵ľͻ�������
        if (ct != null) {
            //����ԭ��bean,���Ի����ҵ��Ĺ��췽��,�����´ι���ʵ������.��BeanDefinition�л�ȡ�������ù��췽���ķ���
            //ͬʱ���������Ӵ�bd�л�ȡ���߼�
            if (bd.isPrototype()) {
                bd.setConstructor(ct);
            }
            return ct;
        } else {
            throw new Exception("�����ڶ�Ӧ�Ĺ��췽����" + bd);
        }
    }

    //���ҹ��������ķ���
    private Method determineFactoryMethod(BeanDefinition bd, Object[] args, Class<?> type) throws Exception {
        if (type == null) {
            type = bd.getBeanClass();
        }
        //��ȡbean�����й�������������
        String methodName = bd.getFactoryMethodName();
//        �������Ϊ�վͷ����޲εķ���
        if (args == null) {
            return type.getMethod(methodName, null);
        }
        Method m = null;
        //����ԭ��bean,�ӵڶ��ο�ʼ��ȡbeanʵ��ʱ,��ֱ�ӻ�õ�һ�λ���Ĺ��췽��
        m = bd.getFactoryMethod();
        if (m != null) {
            return m;
        }
        //�ȸ��ݲ������ͻ�ȡ��ȷƥ��ķ���
        Class[] paramTypes = new Class[args.length];
        int j = 0;
        for (Object p : args) {
            paramTypes[j++] = p.getClass();
        }

        try {
            m = type.getMethod(methodName, paramTypes);
        } catch (Exception e) {
        }
        //������ݲ������ͽ��о�ȷ���β���û���ҵ���������,�ͻ�����еĹ��췽��������ͨ�������������ˣ��ٱȶ��β�������ʵ������
        if (m == null) {
            // û�о�ȷ��������ƥ��ģ������ƥ�����еķ���
            // �ж��߼������жϲ��������������αȶ��β�������ʵ������
            outer:
            for (Method m0 : type.getMethods()) {
                if (!m0.getName().equals(methodName)) {
                    continue;
                }
                Class<?>[] paramterTypes = m.getParameterTypes();
                if (paramterTypes.length == args.length) {
                    for (int i = 0; i < paramterTypes.length; i++) {
                        //isAssignableFrom������ʾ�Ƿ���԰�args[i].getClass()��ֵ��paramterTypes[i]
                        if (!paramterTypes[i].isAssignableFrom(args[i].getClass())) {
                            continue outer;
                        }
                    }
                    m = m0;
                    break outer;
                }
            }
        }

        //����ҵ����췽���ˣ�������ԭ�͵ľͻ�������
        if (m != null) {
            // ����ԭ��bean,���Ի����ҵ��ķ����������´ι���ʵ��������BeanDefinfition�л�ȡ�������÷����ķ�����
            // ͬʱ���������Ӵ�beanDefinition�л�ȡ���߼���
            if (bd.isPrototype()) {
                bd.setFactoryMethod(m);
            }
            return m;
        } else {
            throw new Exception("�����ڶ�Ӧ�Ĺ��췽����" + bd);
        }
    }

    private void setPropertyDIValues(BeanDefinition bd, Object instance) throws Exception {
        //bean������û������������ֱ�ӷ���
        if (CollectionUtils.isEmpty(bd.getPropertyValues())) {
            return;
        }
        //���bean����������������������ֵ,���÷�ʽ�͹������������һ��
        for (PropertyValue pv : bd.getPropertyValues()) {
            //��������û�и����־�ֱ������
            if (StringUtils.isBlank(pv.getName())) {
                continue;
            }
            //��ȡ�����
            Class<?> clazz = instance.getClass();
            //ͨ����������ȡ����������������ֶ�
            Field p = clazz.getDeclaredField(pv.getName());

            //�����ֶοɷ���
            p.setAccessible(true);

            //������ֵת��Ϊ������ֵ,�͹������һ��
            Object rv = pv.getValue();
            Object v = null;
            if (rv == null) {
                v = null;
            } else if (rv instanceof BeanReference) {
                v = this.doGetBean(((BeanReference) rv).getBeanName());
            } else if (rv instanceof Object[]) {
                // TODO �������е�bean����
            } else if (rv instanceof Collection) {
                // TODO �������е�bean����
            } else if (rv instanceof Properties) {
                // TODO ����properties�е�bean����
            } else if (rv instanceof Map) {
                // TODO ����Map�е�bean����
            } else {
                v = rv;
            }
            //��������ֵv���õ�����p���� ������p������ֵv
            p.set(instance, v);

        }
    }
}

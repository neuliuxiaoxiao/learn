package spring.ioc;

import org.apache.commons.lang3.StringUtils;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

import java.io.Closeable;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Title DefaultBeanFactory
 * @Description TODO
 * @Author liuxi58
 * @Date 2019/9/26 18:57
 **/
public class DefaultBeanFactory implements BeanFactory, BeanDefinitionRegistry, Closeable {

    private final Log logger = LogFactory.getLog(getClass());
    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(256);
    private Map<String, Object> beanMap = new ConcurrentHashMap<>(256);

    @Override
    public Object getBean(String beanName) throws Exception {
        return this.doGetBean(beanName);
    }

    private Object doGetBean(String beanName) throws Exception {
        Objects.requireNonNull(beanName, "beanName不能为空");
        Object instance = beanMap.get(beanName);
        if (instance != null) {
            return instance;
        }
        BeanDefinition bd = this.getBeanDefinition(beanName);
        Objects.requireNonNull(bd, "BeanDefinition不能为空");
        Class<?> type = bd.getBeanClass();
        if (type != null) {
            if (StringUtils.isBlank(bd.getFactoryMethodName())) {
                instance = this.createInstanceByConstructor(bd);
            }else {
                instance = this.createInstanceByStaticFactoryMethod(bd);
            }
        }else {
            instance = this.createInstanceByFactoryBean(bd);
        }
        this.doInit(bd,instance);
        if (bd.isSingleton()){
            beanMap.put(beanName,instance);
        }
        return instance;
    }

    private Object createInstanceByConstructor(BeanDefinition bd) throws InstantiationException, IllegalAccessException {
        try {
            return bd.getBeanClass().newInstance();
        } catch (SecurityException e1) {
            logger.error("创建bean的实例异常,beanDefinition：" + bd, e1);
            throw e1;
        }
    }

    private Object createInstanceByStaticFactoryMethod(BeanDefinition bd) throws Exception {
        Class<?> type = bd.getBeanClass();
        Method m = type.getMethod(bd.getFactoryMethodName(), null);
        return m.invoke(type, null);
    }

    private Object createInstanceByFactoryBean(BeanDefinition bd) throws Exception {
        Object factoryBean = this.doGetBean(bd.getFactoryBeanName());
        Method m = factoryBean.getClass().getMethod(bd.getFactoryMethodName(), null);
        return m.invoke(factoryBean, null);
    }

    private void doInit(BeanDefinition bd, Object instance) throws Exception {
        if (StringUtils.isNotBlank(bd.getInitMethodName())) {
            Method m = instance.getClass().getMethod(bd.getInitMethodName(), null);
            m.invoke(instance, null);
        }
    }

    @Override
    public void close() throws IOException {
        for (Map.Entry<String, BeanDefinition> e : this.beanDefinitionMap.entrySet()) {
            String beanName = e.getKey();
            BeanDefinition bd = e.getValue();
            if (bd.isSingleton()&&StringUtils.isNotBlank(bd.getDestroyMethodName())){
                Object instance = this.beanMap.get(beanName);
                try {
                    Method m = instance.getClass().getMethod(bd.getDestroyMethodName(),null);
                    m.invoke(instance,null);
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e1) {
                    e1.printStackTrace();
                    logger.error("执行bean[" + beanName + "] " + bd + " 的 销毁方法异常！", e1);
                }
            }
        }
    }

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) throws BeanDefinitionRegistException {
        Objects.requireNonNull(beanName, "注册bean需要给入beanName");
        Objects.requireNonNull(beanDefinition, "注册bean需要给入beanDefition");
        if (!beanDefinition.validate()) {
            throw new BeanDefinitionRegistException("名字为[" + beanName + "]的bean定义不合法：" + beanDefinition);
        }
        if (this.containsBeanDefinition(beanName)) {
            throw new BeanDefinitionRegistException("名字为[" + beanName + "]的bean定义已存在：" + this.getBeanDefinition(beanName));
        }
        this.beanDefinitionMap.put(beanName, beanDefinition);
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) {
        return this.beanDefinitionMap.get(beanName);
    }

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return this.beanDefinitionMap.containsKey(beanName);
    }
}

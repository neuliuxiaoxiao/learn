package spring.aop;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;
import spring.aop.advisor.Advisor;
import spring.aop.advisor.AdvisorRegistry;
import spring.aop.advisor.PointcutAdvisor;
import spring.aop.pointcut.Pointcut;
import spring.beans.BeanFactory;
import spring.beans.BeanFactoryAware;
import spring.beans.BeanPostProcessor;

import java.lang.reflect.Method;
import java.util.*;

/**
 * @Title AdvisorAutoProxyCreator
 * @Description AOP��ǿ����Ĺ۲���ʵ��
 * @Author liuxi58
 * @Date 2019/9/27 18:33
 **/
public class AdvisorAutoProxyCreator implements AdvisorRegistry, BeanPostProcessor, BeanFactoryAware {

    private List<Advisor> advisors;

    private BeanFactory beanFactory;

    public AdvisorAutoProxyCreator() {
        this.advisors = new ArrayList<>();
    }

    @Override
    public void registAdvisor(Advisor advisor) {
        this.advisors.add(advisor);
    }

    @Override
    public List<Advisor> getAdvisors() {
        return advisors;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    //������ǿ
    public Object postProcessAfterInitialization(Object bean, String beanName) throws Throwable {
        //�ڴ��ж�bean�Ƿ���Ҫ����������ǿ
        List<Advisor> matchAdvisors = getMatchedAdvisors(bean, beanName);
        //�����Ҫ�ͽ�����ǿ,�ٷ�����ǿ�Ķ���
        if (CollectionUtils.isNotEmpty(matchAdvisors)) {
            bean =this.createProxy(bean,beanName,matchAdvisors);
        }
        return bean;
    }

    //�ڴ��ж�bean�Ƿ���Ҫ����������ǿ
    private List<Advisor> getMatchedAdvisors(Object bean, String beanName) {
        if (CollectionUtils.isEmpty(advisors)){
            return null;
        }
        //�õ��ࡢ������з���
        Class<?> beanClass = bean.getClass();
        List<Method> allMethods = this.getAllMethodForClass(beanClass);

        //���ƥ���Advisor��list
        List<Advisor> matchAdvisors = new ArrayList<>();
        //����Advisor����ƥ���
        for (Advisor ad : this.advisors) {
            if (ad instanceof PointcutAdvisor){
                if (isPointcutMatchBean((PointcutAdvisor) ad, beanClass, allMethods)) {
                    matchAdvisors.add(ad);
                }
            }
        }
        return matchAdvisors;
    }
    //��ȡ������з����������̳еĸ����ʵ�ֵĽӿ�����ķ���
    private List<Method> getAllMethodForClass(Class<?> beanClass){
        List<Method> allMethods  = new LinkedList<>();
        //��ȡbeanClass�����нӿ�
        Set<Class<?>> classes = new LinkedHashSet<>(ClassUtils.getAllInterfacesForClassAsSet(beanClass));
        classes.add(beanClass);
        //�������е���ͽӿڷ����ȡ�����еķ���
        for (Class<?> clazz : classes) {
            Method[] methods = ReflectionUtils.getAllDeclaredMethods(clazz);
            for (Method m : methods) {
                allMethods.add(m);
            }
        }
        return allMethods;
    }
    //�ж��༰��ķ����Ƿ������ƥ��
    private boolean isPointcutMatchBean(PointcutAdvisor pa,Class<?> beanClass,List<Method> methods){
        Pointcut p = pa.getPointcut();
        //�����ж����Ƿ�ƥ��
        if (!p.matchsClass(beanClass)){
            return false;
        }
        //���ж��Ƿ��з���ƥ��
        for (Method method : methods) {
            if (p.matchsMethod(method,beanClass)){
                return true;
            }
        }
        return false;
    }
    //�������������ǿ
    private Object createProxy(Object bean,String beanName,List<Advisor> matchAdvisor) throws Throwable {
        //ͨ��AOPProxyFactory����ȥ���ѡ�񡢺ʹ����������Ĺ���
        return AopProxyFactory.getDefaultAopProxyFactory().createAopProxy(bean,beanName,matchAdvisor,beanFactory).getProxy();
    }
}

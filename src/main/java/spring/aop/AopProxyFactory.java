package spring.aop;

import spring.aop.advisor.Advisor;
import spring.beans.BeanFactory;

import java.util.List;

/**
 * @Title AopProxyFactory
 * @Description ����AopProxyFactory����ѡ��ʹ���ĸ���̬����
 * @Author liuxi58
 * @Date 2019/9/27 19:31
 **/
public interface AopProxyFactory {
    AopProxy createAopProxy(Object bean, String beanName, List<Advisor> matchAdvisors, BeanFactory beanFactory)
            throws Throwable;

    //���Ĭ�ϵ�AopProxyFactoryʵ��
    static AopProxyFactory getDefaultAopProxyFactory() {
        return new DefaultAopProxyFactory();
    }
}

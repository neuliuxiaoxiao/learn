package spring.aop;

import spring.aop.advisor.Advisor;
import spring.beans.BeanFactory;

import java.util.List;

/**
 * @Title AopProxyFactory
 * @Description 工厂AopProxyFactory负责选择使用哪个动态代理
 * @Author liuxi58
 * @Date 2019/9/27 19:31
 **/
public interface AopProxyFactory {
    AopProxy createAopProxy(Object bean, String beanName, List<Advisor> matchAdvisors, BeanFactory beanFactory)
            throws Throwable;

    //获得默认的AopProxyFactory实例
    static AopProxyFactory getDefaultAopProxyFactory() {
        return new DefaultAopProxyFactory();
    }
}

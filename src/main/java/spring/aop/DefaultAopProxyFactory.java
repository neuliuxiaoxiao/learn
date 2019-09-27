package spring.aop;

import spring.aop.advisor.Advisor;
import spring.beans.BeanFactory;

import java.util.List;

/**
 * @Title DefaultAopProxyFactory
 * @Description 工厂AopProxyFactory的默认实现   负责选择使用哪个动态代理
 * @Author liuxi58
 * @Date 2019/9/27 20:15
 **/
public class DefaultAopProxyFactory implements AopProxyFactory {

    @Override
    public AopProxy createAopProxy(Object bean, String beanName, List<Advisor> matchAdvisors, BeanFactory beanFactory) throws Throwable {
        // 是该用jdk动态代理还是cglib？
        if (shouldUseJDKDynamicProxy(bean, beanName)) {
            return new JdkDynamicAopProxy(beanName, bean, matchAdvisors, beanFactory);
        } else {
            return new CglibDynamicAopProxy(beanName, bean, matchAdvisors, beanFactory);
        }
    }

    //默认使用cglib
    private boolean shouldUseJDKDynamicProxy(Object bean, String beanName) {
        // 如何判断？
        // 这样可以吗：有实现接口就用JDK,没有就用cglib？
        return false;
    }
}

package spring.aop;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import spring.aop.advisor.Advisor;
import spring.beans.BeanDefinition;
import spring.beans.BeanFactory;
import spring.beans.DefaultBeanFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @Title CglibDynamicAopProxy
 * @Description cglib动态AOP代理实现
 * @Author liuxi58
 * @Date 2019/9/27 19:49
 **/
public class CglibDynamicAopProxy implements AopProxy, MethodInterceptor {
    private static final Log logger = LogFactory.getLog(CglibDynamicAopProxy.class);
    private static Enhancer enhancer = new Enhancer();
    private String beanName;
    private Object target;
    private List<Advisor> matchAdvisors;
    private BeanFactory beanFactory;

    public CglibDynamicAopProxy(String beanName, Object target, List<Advisor> matchAdvisors, BeanFactory beanFactory) {
        super();
        this.beanName = beanName;
        this.target = target;
        this.matchAdvisors = matchAdvisors;
        this.beanFactory = beanFactory;
    }


    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        return AopProxyUtils.applyAdvices(target,method,objects,matchAdvisors,o,beanFactory);
    }

    @Override
    public Object getProxy() {
        return this.getProxy(target.getClass().getClassLoader());
    }

    @Override
    public Object getProxy(ClassLoader classLoader) {
        if (logger.isDebugEnabled()) {
            logger.debug("为" + target + "创建cglib代理。");
        }
        Class<?> superClass = this.target.getClass();
        enhancer.setSuperclass(superClass);
        enhancer.setInterfaces(this.getClass().getInterfaces());
        enhancer.setCallback(this);
        Constructor<?> constructor = null;
        try {
            constructor = superClass.getConstructor(new Class<?>[]{});
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        if (constructor!=null){
            return enhancer.create();
        }else {
            //没有无参构造函数时，从beanDefinition里面获取构造参数的类型和值进行增强
            BeanDefinition bd = ((DefaultBeanFactory)beanFactory).getBeanDefinition(beanName);
            return enhancer.create(bd.getConstructor().getParameterTypes(),bd.getConstructorArgumentValues());
        }
    }
}

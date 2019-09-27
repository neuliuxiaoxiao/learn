package spring.aop;

import org.springframework.util.CollectionUtils;
import spring.aop.advisor.Advisor;
import spring.aop.advisor.PointcutAdvisor;
import spring.beans.BeanFactory;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @Title AopProxyUtils
 * @Description 增强逻辑实现工具类
 * @Author liuxi58
 * @Date 2019/9/27 19:34
 **/
public class AopProxyUtils {

    public static Object applyAdvices(Object target, Method method, Object[] args, List<Advisor> matchAdvisors,Object proxy, BeanFactory beanFactory) throws Throwable {
        // 这里要做什么？
        // 1、获取要对当前方法进行增强的advice
        List<Object> advices = AopProxyUtils.getShouldApplyAdvices(target.getClass(),method,matchAdvisors,beanFactory);

        // 2、如有增强的advice，就责任链式增强执行
        if (CollectionUtils.isEmpty(advices)){
            //没有Advice就直接调用invoke方法
            return method.invoke(target,args);
        }else {
            //有Advice就责任链式执行增强
            AopAdviceChainInvocation chain = new AopAdviceChainInvocation(proxy, target, method, args, advices);
            return chain.invoke();
        }


    }
    /**
     * 获取与方法匹配的切面的advices
     *
     * @param beanClass
     * @param method
     * @param matchAdvisors
     * @param beanFactory
     * @return
     * @throws Exception
     */
    public static List<Object> getShouldApplyAdvices(Class<?> beanClass,Method method,List<Advisor> matchAdvisors,BeanFactory beanFactory) throws Throwable{
        if (CollectionUtils.isEmpty(matchAdvisors)){
            return null;
        }
        List<Object> advices = new ArrayList<>();
        for (Advisor advisor : matchAdvisors) {
            if (advisor instanceof PointcutAdvisor){
                //如果当前方法和切入点匹配就是要加入增强功能的方法
                if (((PointcutAdvisor) advisor).getPointcut().matchsMethod(method,beanClass)){
                    advices.add(beanFactory.getBean(advisor.getAdviceBeanName()));
                }
            }
        }
        return advices;
    }
}

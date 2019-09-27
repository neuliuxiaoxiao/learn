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
 * @Description ��ǿ�߼�ʵ�ֹ�����
 * @Author liuxi58
 * @Date 2019/9/27 19:34
 **/
public class AopProxyUtils {

    public static Object applyAdvices(Object target, Method method, Object[] args, List<Advisor> matchAdvisors,Object proxy, BeanFactory beanFactory) throws Throwable {
        // ����Ҫ��ʲô��
        // 1����ȡҪ�Ե�ǰ����������ǿ��advice
        List<Object> advices = AopProxyUtils.getShouldApplyAdvices(target.getClass(),method,matchAdvisors,beanFactory);

        // 2��������ǿ��advice����������ʽ��ǿִ��
        if (CollectionUtils.isEmpty(advices)){
            //û��Advice��ֱ�ӵ���invoke����
            return method.invoke(target,args);
        }else {
            //��Advice��������ʽִ����ǿ
            AopAdviceChainInvocation chain = new AopAdviceChainInvocation(proxy, target, method, args, advices);
            return chain.invoke();
        }


    }
    /**
     * ��ȡ�뷽��ƥ��������advices
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
                //�����ǰ�����������ƥ�����Ҫ������ǿ���ܵķ���
                if (((PointcutAdvisor) advisor).getPointcut().matchsMethod(method,beanClass)){
                    advices.add(beanFactory.getBean(advisor.getAdviceBeanName()));
                }
            }
        }
        return advices;
    }
}

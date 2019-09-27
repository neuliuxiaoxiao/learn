package spring.aop;

import spring.aop.advisor.Advisor;
import spring.beans.BeanFactory;

import java.util.List;

/**
 * @Title DefaultAopProxyFactory
 * @Description ����AopProxyFactory��Ĭ��ʵ��   ����ѡ��ʹ���ĸ���̬����
 * @Author liuxi58
 * @Date 2019/9/27 20:15
 **/
public class DefaultAopProxyFactory implements AopProxyFactory {

    @Override
    public AopProxy createAopProxy(Object bean, String beanName, List<Advisor> matchAdvisors, BeanFactory beanFactory) throws Throwable {
        // �Ǹ���jdk��̬������cglib��
        if (shouldUseJDKDynamicProxy(bean, beanName)) {
            return new JdkDynamicAopProxy(beanName, bean, matchAdvisors, beanFactory);
        } else {
            return new CglibDynamicAopProxy(beanName, bean, matchAdvisors, beanFactory);
        }
    }

    //Ĭ��ʹ��cglib
    private boolean shouldUseJDKDynamicProxy(Object bean, String beanName) {
        // ����жϣ�
        // ������������ʵ�ֽӿھ���JDK,û�о���cglib��
        return false;
    }
}

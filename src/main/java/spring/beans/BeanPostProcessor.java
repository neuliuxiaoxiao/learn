package spring.beans;

/**
 * @Title BeanPostProcessor
 * @Description AOP��ǿ����ӿ�
 * @Author liuxi58
 * @Date 2019/9/27 18:29
 **/
public interface BeanPostProcessor {

    //bean��ʼ��ǰ��ǿ
    default Object postProcessBeforeInitialization(Object bean, String beanName) throws Throwable {
        return bean;
    }
    //bean��ʼ������ǿ
    default Object postProcessAfterInitialization(Object bean, String beanName) throws Throwable {
        return bean;
    }
}

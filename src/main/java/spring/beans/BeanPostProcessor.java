package spring.beans;

/**
 * @Title BeanPostProcessor
 * @Description AOP增强处理接口
 * @Author liuxi58
 * @Date 2019/9/27 18:29
 **/
public interface BeanPostProcessor {

    //bean初始化前增强
    default Object postProcessBeforeInitialization(Object bean, String beanName) throws Throwable {
        return bean;
    }
    //bean初始化后增强
    default Object postProcessAfterInitialization(Object bean, String beanName) throws Throwable {
        return bean;
    }
}

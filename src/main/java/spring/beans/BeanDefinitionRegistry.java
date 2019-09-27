package spring.beans;

/**
 * @Title BeanDefinitionRegistry
 * @Description bean定义BeanDefinition定义好了就需要告诉IOC容器(bean工厂):
 * 通过bean定义注册接口BeanDefinitionRegistry把bean定义BeanDefinition注册到IOC容器(bean工厂)BeanFactory
 * @Author liuxi58
 * @Date 2019/9/27 10:55
 **/
public interface BeanDefinitionRegistry {

    /**
     * @Author liuxi58
     * @Date 2019/9/27 11:08
     * @Param beanName : bean的名字
     * bd : bean的定义
     * @Return void
     * @Description 注册bean的定义
     **/
    void registerBeanDefinition(String beanName, BeanDefinition bd) throws BeanDefinitionRegistException;

    /**
     * @Author liuxi58
     * @Date 2019/9/27 11:09
     * @Param beanName : bean的名字
     * @Return spring.beans.BeanDefinition
     * @Description 获取bean的定义
     **/
    BeanDefinition getBeanDefinition(String beanName);

    /**
     * @Author liuxi58
     * @Date 2019/9/27 11:09
     * @Param beanName : bean的名字
     * @Return boolean true 包含 false 不包含
     * @Description 判断是否包含bean 的定义
     **/
    boolean containsBeanDefinition(String beanName);
}

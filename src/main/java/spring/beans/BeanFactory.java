package spring.beans;

/**
   *@Author liuxi58
   *@Date 2019/9/27 10:53
   *@Description IOC容器接口：负责创建bean实例
   **/
public interface BeanFactory {
    /**
       *@Author liuxi58
       *@Date 2019/9/27 10:54
       *@Param beanName : bean的名字
       *@Return java.lang.Object bean实例
       *@Description  获取bean
       **/
    Object getBean(String beanName) throws Exception;

    /**
     * 注册aop织入（注册AOP增强处理的观察者实现）
     */
    void resiterBeanPostProcessor(BeanPostProcessor bpp);
}

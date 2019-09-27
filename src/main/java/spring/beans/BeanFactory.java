package spring.beans;

/**
   *@Author liuxi58
   *@Date 2019/9/27 10:53
   *@Description IOC�����ӿڣ����𴴽�beanʵ��
   **/
public interface BeanFactory {
    /**
       *@Author liuxi58
       *@Date 2019/9/27 10:54
       *@Param beanName : bean������
       *@Return java.lang.Object beanʵ��
       *@Description  ��ȡbean
       **/
    Object getBean(String beanName) throws Exception;

    /**
     * ע��aop֯�루ע��AOP��ǿ����Ĺ۲���ʵ�֣�
     */
    void resiterBeanPostProcessor(BeanPostProcessor bpp);
}

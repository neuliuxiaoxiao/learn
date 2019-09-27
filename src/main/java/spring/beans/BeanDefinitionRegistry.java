package spring.beans;

/**
 * @Title BeanDefinitionRegistry
 * @Description bean����BeanDefinition������˾���Ҫ����IOC����(bean����):
 * ͨ��bean����ע��ӿ�BeanDefinitionRegistry��bean����BeanDefinitionע�ᵽIOC����(bean����)BeanFactory
 * @Author liuxi58
 * @Date 2019/9/27 10:55
 **/
public interface BeanDefinitionRegistry {

    /**
     * @Author liuxi58
     * @Date 2019/9/27 11:08
     * @Param beanName : bean������
     * bd : bean�Ķ���
     * @Return void
     * @Description ע��bean�Ķ���
     **/
    void registerBeanDefinition(String beanName, BeanDefinition bd) throws BeanDefinitionRegistException;

    /**
     * @Author liuxi58
     * @Date 2019/9/27 11:09
     * @Param beanName : bean������
     * @Return spring.beans.BeanDefinition
     * @Description ��ȡbean�Ķ���
     **/
    BeanDefinition getBeanDefinition(String beanName);

    /**
     * @Author liuxi58
     * @Date 2019/9/27 11:09
     * @Param beanName : bean������
     * @Return boolean true ���� false ������
     * @Description �ж��Ƿ����bean �Ķ���
     **/
    boolean containsBeanDefinition(String beanName);
}

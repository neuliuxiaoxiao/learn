package spring.beans;

/**
 * @Title BeanReference
 * @Description ��������ע��������bean����
 * @Author liuxi58
 * @Date 2019/9/27 14:06
 **/
public class BeanReference {

    private String beanName;

    public BeanReference(String beanName) {
        super();
        this.beanName = beanName;
    }

    /**
     * ������õ�beanName
     */
    public String getBeanName(){
        return  this.beanName;
    }
}

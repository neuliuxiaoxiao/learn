package spring.beans;

/**
 * @Title BeanReference
 * @Description 用于依赖注入中描述bean依赖
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
     * 获得引用的beanName
     */
    public String getBeanName(){
        return  this.beanName;
    }
}

package spring.beans;

/**
 * @Title BeanFactoryAware
 * @Description
 * @Author liuxi58
 * @Date 2019/9/27 18:34
 **/
public interface BeanFactoryAware extends Aware {

    void setBeanFactory(BeanFactory bf);
}

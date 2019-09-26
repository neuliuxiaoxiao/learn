package spring.ioc;

/**
 * @Title BeanDefinitionRegistException
 * @Description TODO
 * @Author liuxi58
 * @Date 2019/9/26 18:31
 **/
public class BeanDefinitionRegistException extends Exception {

    private static final long serialVersionUID = 794582617504624147L;

    public BeanDefinitionRegistException(String mess){
        super(mess);
    }
    public BeanDefinitionRegistException(String mess,Throwable e){
        super(mess,e);
    }
}

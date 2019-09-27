package spring.beans;

/**
 * @Title BeanDefinitionRegistException
 * @Description TODO
 * @Author liuxi58
 * @Date 2019/9/27 11:06
 **/
public class BeanDefinitionRegistException extends Exception {

    public BeanDefinitionRegistException(String message) {
        super(message);
    }

    public BeanDefinitionRegistException(String message, Throwable cause) {
        super(message, cause);
    }
}

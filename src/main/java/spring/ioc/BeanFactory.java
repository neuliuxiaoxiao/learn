package spring.ioc;

public interface BeanFactory {

    public Object getBean(String beanName) throws Exception;
}

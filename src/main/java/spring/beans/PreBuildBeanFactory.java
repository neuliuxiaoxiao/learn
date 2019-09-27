package spring.beans;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title PreBuildBeanFactory
 * @Description 提前实例化单例bean
 * @Author liuxi58
 * @Date 2019/9/27 12:23
 **/
public class PreBuildBeanFactory extends DefaultBeanFactory {

    private final Log logger = LogFactory.getLog(getClass());

    private List<String> beanNames = new ArrayList<>();

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition bd) throws BeanDefinitionRegistException {
        super.registerBeanDefinition(beanName,bd);
        synchronized (beanNames){
            beanNames.add(beanName);
        }
    }
    public void preInstantiateSingletons() throws Exception{
        synchronized (beanNames){
            for (String name : beanNames) {
                BeanDefinition bd = this.getBeanDefinition(name);
                if (bd.isSingleton()){
                    this.doGetBean(name);
                }
            }
        }
    }
}

package spring.ioc;

import org.apache.commons.lang3.StringUtils;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title PreBuildBeanFactory
 * @Description TODO
 * @Author liuxi58
 * @Date 2019/9/26 19:22
 **/
public class PreBuildBeanFactory extends DefaultBeanFactory {

    private final Log logger = LogFactory.getLog(getClass());
    private List<String> beanNames = new ArrayList<>();

    @Override
    public void registerBeanDefinition(String beanName,BeanDefinition beanDefinition) throws BeanDefinitionRegistException{
        super.registerBeanDefinition(beanName,beanDefinition);
        synchronized (beanNames){
            beanNames.add(beanName);
        }
    }
    public void preInstantiateSingletons() throws Exception{
        synchronized (beanNames){
            for (String beanName : beanNames) {
                BeanDefinition bd = this.getBeanDefinition(beanName);
                if (bd.isSingleton()){
                    this.getBean(beanName);
                    if (logger.isDebugEnabled()){
                        logger.debug("preInstantiate:name="+beanName+"--"+bd);
                    }
                }
            }
        }
    }
}

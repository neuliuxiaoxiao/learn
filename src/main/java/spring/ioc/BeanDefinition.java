package spring.ioc;

import org.apache.commons.lang3.StringUtils;

public interface BeanDefinition {

    String SCOPE_SINGLETION = "singleton";
    String SCOPE_PROTOTYPE = "prototype";

    Class<?> getBeanClass();

    String getScope();

    boolean isSingleton();

    boolean isPrototype();

    String getFactoryBeanName();

    String getFactoryMethodName();

    String getInitMethodName();

    String getDestroyMethodName();

    default boolean validate() {
        if (this.getBeanClass() == null) {
            if (StringUtils.isBlank(getFactoryBeanName()) || StringUtils.isBlank(getFactoryMethodName())) {
                return false;
            }
        }
        if (this.getBeanClass() != null && StringUtils.isNotBlank(getFactoryBeanName())) {
            return true;
        }
        return true;
    }
}

package spring.beans;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @Title BeanDefinition
 * @Description bean定义接口:要IOC容器(bean工厂)创建bean实例，就得告诉IOC容器(bean工厂)需要创建什么样的bean-BeanDefinition
 * @Author liuxi58
 * @Date 2019/9/27 10:56
 **/
public interface BeanDefinition {

    String SCOPE_SINGLETON = "singleton";
    String SCOPE_PROTOTYPE = "prototype";

    /**
     * 类：new 构造方法的方式创建bean时，需要告诉bean工厂怎么获取类的名称
     */
    Class<?> getBeanClass();

    /**
     * 获取scope
     */
    String getScope();

    /**
     * 是否单例
     */
    boolean isSingleton();

    /**
     * 是否原型
     */
    boolean isPrototype();

    /**
     * 工厂bean名：成员工厂方法的方式创建bean时，需要告诉bean工厂怎么获取工厂bean名
     */
    String getFactoryBeanName();

    /**
     * 工厂方法名：静态工厂方法的方式创建bean时，需要告诉bean工厂怎么获取工厂方法名
     */
    String getFactoryMethodName();

    /**
     * 获取初始化方法名
     */
    String getInitMethodName();

    /**
     * 获取销毁方法名
     */
    String getDestoryMethodName();

    /**
     * 获取构造参数值
     */
    List<?> getConstructorArgumentValues();

    public Object[] getConstructorArgumentRealValues();

    public void setConstructorArgumentRealValues(Object[] values);

    /* 下面四格方法是供beanFactory中使用的*/

    /**
     * 获取构造方法
     */
    public Constructor<?> getConstructor();

    /**
     * 缓存构造方法
     */
    public void setConstructor(Constructor<?> constructor);

    /**
     * 获取工厂方法
     */
    public Method getFactoryMethod();

    /**
     * 缓存工厂方法
     */
    public void setFactoryMethod(Method factoryMethod);

    /**
     * 获取属性依赖定义
     */
    List<PropertyValue> getPropertyValues();

    /**
     * 校验bean定义的合法性
     */
    default boolean validate() {
        //没定义class，工厂bean或者工厂方法没指定，则不合法
        if (this.getBeanClass() == null) {
            if (StringUtils.isBlank(getFactoryBeanName()) || StringUtils.isBlank(getFactoryMethodName())) {
                return false;
            }
        }
        //定义了class，又定义工厂bean，不合法
        if (this.getBeanClass() != null && StringUtils.isNotBlank(getFactoryBeanName())) {
            return false;
        }
        return true;
    }
}

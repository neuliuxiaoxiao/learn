package spring.beans;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;

/**
 * @Author liuxi58
 * @Date 2019/9/27 11:16
 * @Description bean定义有了就需要实现bean定义接口BeanDefinition，实现一个通用的bean定义GenericBeanDefinition来承载数据
 **/
public class GenericBeanDefinition implements BeanDefinition {

    /**
     * bean的class
     */
    private Class<?> beanClass;
    /**
     * scope默认单例
     */
    private String scope = BeanDefinition.SCOPE_SINGLETON;
    /**
     * 工厂bean的名字
     */
    private String factoryBeanName;
    /**
     * 工厂方法的名字
     */
    private String factoryMethodName;
    /**
     * 初始化方法名
     */
    private String initMethodName;
    /**
     * 销毁方法名
     */
    private String destroyMethodName;

    /**
     * 构造参数存放舒心
     */
    private List<?> constructorArgumentValues;

    /**
     * 构造方法
     */
    private Constructor constructor;
    /**
     * 工厂方法
     */
    private Method factoryMethod;
    /**
     * 存放属性依赖
     */
    private List<PropertyValue> propertyValues;

    public void setBeanClass(Class<?> beanClass) {
        this.beanClass = beanClass;
    }

    public void setScope(String scope) {
        if (StringUtils.isNotBlank(scope)) {
            this.scope = scope;
        }
    }

    public void setConstructorArgumentValues(List<?> constructorArgumentValues) {
        this.constructorArgumentValues = constructorArgumentValues;
    }

    public void setFactoryBeanName(String factoryBeanName) {
        this.factoryBeanName = factoryBeanName;
    }

    public void setFactoryMethodName(String factoryMethodName) {
        this.factoryMethodName = factoryMethodName;
    }

    public void setInitMethodName(String initMethodName) {
        this.initMethodName = initMethodName;
    }

    public void setDestroyMethodName(String destroyMethodName) {
        this.destroyMethodName = destroyMethodName;
    }

    @Override
    public Class<?> getBeanClass() {
        return this.beanClass;
    }

    @Override
    public String getScope() {
        return this.scope;
    }

    @Override
    public boolean isSingleton() {
        return BeanDefinition.SCOPE_SINGLETON.equals(this.scope);
    }

    @Override
    public boolean isPrototype() {
        return BeanDefinition.SCOPE_PROTOTYPE.equals(this.scope);
    }

    @Override
    public String getFactoryBeanName() {
        return this.factoryBeanName;
    }

    @Override
    public String getFactoryMethodName() {
        return this.factoryMethodName;
    }

    @Override
    public String getInitMethodName() {
        return this.initMethodName;
    }

    @Override
    public String getDestoryMethodName() {
        return this.destroyMethodName;
    }

    @Override
    public List<?> getConstructorArgumentValues() {
        return constructorArgumentValues;
    }

    @Override
    public Constructor<?> getConstructor() {
        return constructor;
    }

    @Override
    public void setConstructor(Constructor<?> constructor) {
        this.constructor=constructor;
    }

    @Override
    public Method getFactoryMethod() {
        return factoryMethod;
    }

    @Override
    public void setFactoryMethod(Method factoryMethod) {
        this.factoryMethod = factoryMethod;
    }

    @Override
    public List<PropertyValue> getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(List<PropertyValue> propertyValues) {
        this.propertyValues = propertyValues;
    }

    @Override
    public String toString() {
        return "GenericBeanDefinition{" +
                "beanClass=" + beanClass +
                ", scope='" + scope + '\'' +
                ", factoryBeanName='" + factoryBeanName + '\'' +
                ", factoryMethodName='" + factoryMethodName + '\'' +
                ", initMethodName='" + initMethodName + '\'' +
                ", destroyMethodName='" + destroyMethodName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenericBeanDefinition that = (GenericBeanDefinition) o;
        return Objects.equals(beanClass, that.beanClass) &&
                Objects.equals(scope, that.scope) &&
                Objects.equals(factoryBeanName, that.factoryBeanName) &&
                Objects.equals(factoryMethodName, that.factoryMethodName) &&
                Objects.equals(initMethodName, that.initMethodName) &&
                Objects.equals(destroyMethodName, that.destroyMethodName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(beanClass, scope, factoryBeanName, factoryMethodName, initMethodName, destroyMethodName);
    }
}

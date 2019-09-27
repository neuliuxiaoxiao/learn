package spring.beans;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @Title BeanDefinition
 * @Description bean����ӿ�:ҪIOC����(bean����)����beanʵ�����͵ø���IOC����(bean����)��Ҫ����ʲô����bean-BeanDefinition
 * @Author liuxi58
 * @Date 2019/9/27 10:56
 **/
public interface BeanDefinition {

    String SCOPE_SINGLETON = "singleton";
    String SCOPE_PROTOTYPE = "prototype";

    /**
     * �ࣺnew ���췽���ķ�ʽ����beanʱ����Ҫ����bean������ô��ȡ�������
     */
    Class<?> getBeanClass();

    /**
     * ��ȡscope
     */
    String getScope();

    /**
     * �Ƿ���
     */
    boolean isSingleton();

    /**
     * �Ƿ�ԭ��
     */
    boolean isPrototype();

    /**
     * ����bean������Ա���������ķ�ʽ����beanʱ����Ҫ����bean������ô��ȡ����bean��
     */
    String getFactoryBeanName();

    /**
     * ��������������̬���������ķ�ʽ����beanʱ����Ҫ����bean������ô��ȡ����������
     */
    String getFactoryMethodName();

    /**
     * ��ȡ��ʼ��������
     */
    String getInitMethodName();

    /**
     * ��ȡ���ٷ�����
     */
    String getDestoryMethodName();

    /**
     * ��ȡ�������ֵ
     */
    List<?> getConstructorArgumentValues();

    public Object[] getConstructorArgumentRealValues();

    public void setConstructorArgumentRealValues(Object[] values);

    /* �����ĸ񷽷��ǹ�beanFactory��ʹ�õ�*/

    /**
     * ��ȡ���췽��
     */
    public Constructor<?> getConstructor();

    /**
     * ���湹�췽��
     */
    public void setConstructor(Constructor<?> constructor);

    /**
     * ��ȡ��������
     */
    public Method getFactoryMethod();

    /**
     * ���湤������
     */
    public void setFactoryMethod(Method factoryMethod);

    /**
     * ��ȡ������������
     */
    List<PropertyValue> getPropertyValues();

    /**
     * У��bean����ĺϷ���
     */
    default boolean validate() {
        //û����class������bean���߹�������ûָ�����򲻺Ϸ�
        if (this.getBeanClass() == null) {
            if (StringUtils.isBlank(getFactoryBeanName()) || StringUtils.isBlank(getFactoryMethodName())) {
                return false;
            }
        }
        //������class���ֶ��幤��bean�����Ϸ�
        if (this.getBeanClass() != null && StringUtils.isNotBlank(getFactoryBeanName())) {
            return false;
        }
        return true;
    }
}

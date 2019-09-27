package spring.v1;

import org.junit.Test;
import spring.beans.BeanReference;
import spring.beans.GenericBeanDefinition;
import spring.beans.PreBuildBeanFactory;
import spring.beans.PropertyValue;
import spring.samples.ABean;
import spring.samples.CBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title PropertyDItest
 * @Description ������������
 * @Author liuxi58
 * @Date 2019/9/27 16:43
 **/
public class PropertyDItest {
    static PreBuildBeanFactory bf = new PreBuildBeanFactory();

    @Test
    public void testPropertyDI() throws Exception {

        //�����������
        GenericBeanDefinition bd = new GenericBeanDefinition();
        bd.setBeanClass(ABean.class);
        List<Object> args = new ArrayList<>();
        args.add("abean01");
        args.add(new BeanReference("cbean"));
        bd.setConstructorArgumentValues(args);
        bf.registerBeanDefinition("abean", bd);
        //�����������
        bd = new GenericBeanDefinition();
        bd.setBeanClass(CBean.class);
        args = new ArrayList<>();
        args.add("cbean01");
        bd.setConstructorArgumentValues(args);
        bf.registerBeanDefinition("cbean", bd);
        //��������
        bd = new GenericBeanDefinition();
        bd.setBeanClass(FBean.class);
        List<PropertyValue> propertyValues = new ArrayList<>();
        propertyValues.add(new PropertyValue("name", "FFBean01"));
        propertyValues.add(new PropertyValue("age", 18));
        propertyValues.add(new PropertyValue("aBean", new BeanReference("abean")));
        bd.setPropertyValues(propertyValues);
        bf.registerBeanDefinition("fbean", bd);
        bf.preInstantiateSingletons();
        FBean fbean = (FBean) bf.getBean("fbean");
        System.out.println("���õ���������ֵ: " + fbean.getName() + " " + fbean.getAge());
        fbean.getaBean().doSomthing();
    }

}

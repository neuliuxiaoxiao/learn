package spring.v1;

import org.junit.Test;
import spring.beans.BeanReference;
import spring.beans.GenericBeanDefinition;
import spring.beans.PreBuildBeanFactory;
import spring.samples.ABean;
import spring.samples.ABeanFactory;
import spring.samples.CBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title DItest
 * @Description TODO
 * @Author liuxi58
 * @Date 2019/9/27 15:57
 **/
public class DItest {

    static PreBuildBeanFactory bf = new PreBuildBeanFactory();
    //构造函数参数依赖注入
    @Test
    public void testConstructorDI() throws Exception {
        System.out.println("1111");
        GenericBeanDefinition bd = new GenericBeanDefinition();
        bd.setBeanClass(ABean.class);
        List<Object> args = new ArrayList<>();
        args.add("abean01");
        args.add(new BeanReference("cbean"));
        bd.setConstructorArgumentValues(args);
        bf.registerBeanDefinition("abean", bd);

        bd = new GenericBeanDefinition();
        bd.setBeanClass(CBean.class);
        args = new ArrayList<>();
        args.add("cbean01");
        bd.setConstructorArgumentValues(args);
        bf.registerBeanDefinition("cbean", bd);

        bf.preInstantiateSingletons();

        ABean abean = (ABean) bf.getBean("abean");

        abean.doSomthing();
    }

    //静态工厂方法参数依赖注入
    @Test
    public void testStaticFactoryMethodDI() throws Exception {
        System.out.println("222");
        GenericBeanDefinition bd = new GenericBeanDefinition();
        bd.setBeanClass(ABeanFactory.class);
        bd.setFactoryMethodName("getABean");
        List<Object> args = new ArrayList<>();
        args.add("abean02");
        args.add(new BeanReference("cbean02"));
        bd.setConstructorArgumentValues(args);
        bf.registerBeanDefinition("abean02", bd);

        bd = new GenericBeanDefinition();
        bd.setBeanClass(CBean.class);
        args = new ArrayList<>();
        args.add("cbean02");
        bd.setConstructorArgumentValues(args);
        bf.registerBeanDefinition("cbean02", bd);

        bf.preInstantiateSingletons();

        ABean abean = (ABean) bf.getBean("abean02");

        abean.doSomthing();
    }

    //普通工厂方法的参数依赖注入
    @Test
    public void testFactoryMethodDI() throws Exception {
        System.out.println("333");
        GenericBeanDefinition bd = new GenericBeanDefinition();
        bd.setFactoryBeanName("abeanFactory");
        bd.setFactoryMethodName("getABean2");
        List<Object> args = new ArrayList<>();
        args.add("abean03");
        args.add(new BeanReference("cbean02"));
        bd.setConstructorArgumentValues(args);
        bf.registerBeanDefinition("abean03", bd);

        bd = new GenericBeanDefinition();
        bd.setBeanClass(ABeanFactory.class);
        bf.registerBeanDefinition("abeanFactory", bd);

        bf.preInstantiateSingletons();

        ABean abean = (ABean) bf.getBean("abean03");

        abean.doSomthing();
    }
}

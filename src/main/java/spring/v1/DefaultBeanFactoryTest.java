package spring.v1;

import org.junit.AfterClass;
import org.junit.Test;
import spring.beans.BeanDefinition;
import spring.beans.DefaultBeanFactory;
import spring.beans.GenericBeanDefinition;
import spring.samples.ABean;
import spring.samples.ABeanFactory;

/**
 * @Title DefaultBeanFactoryTest
 * @Description ����IOC������bean����������beanʵ��ABean
 * @Author liuxi58
 * @Date 2019/9/27 12:29
 **/
public class DefaultBeanFactoryTest {
    static DefaultBeanFactory bf = new DefaultBeanFactory();

    //���Թ��췽����ʽ����beanʵ��
//    @Test
//    public void testRegist() throws Exception {
//        GenericBeanDefinition bd = new GenericBeanDefinition();
//        bd.setBeanClass(ABean.class);
//        bd.setScope(BeanDefinition.SCOPE_SINGLETON);
//        bd.setInitMethodName("init");
//        bd.setDestroyMethodName("destroy");
//        bf.registerBeanDefinition("aBean", bd);
//    }

    //��̬���������ķ�ʽ����beanʵ��
    @Test
    public void testRegistStaticFactoryMethod() throws Exception {
        GenericBeanDefinition bd = new GenericBeanDefinition();
        bd.setBeanClass(ABeanFactory.class);
        bd.setFactoryMethodName("getABean");
        bf.registerBeanDefinition("staticABean", bd);
    }

    //����bean�ķ�ʽ����beanʵ��
//    @Test
//    public void testRegistFactoryMethod() throws Exception {
//        GenericBeanDefinition bd = new GenericBeanDefinition();
//        bd.setBeanClass(ABeanFactory.class);
//        String fname = "factory";
//        bf.registerBeanDefinition(fname, bd);
//        bd = new GenericBeanDefinition();
//        bd.setFactoryBeanName(fname);
//        bd.setFactoryMethodName("getABean2");
//        bd.setScope(BeanDefinition.SCOPE_SINGLETON);
//        bf.registerBeanDefinition("factoryAbean", bd);
//    }

    //��ȡbeanʵ������������ķ���
    @AfterClass
    public static void testGetBean() throws Exception {
        System.out.println("���췽����ʽ------------");
//        for (int i = 0; i < 3; i++) {
//            ABean ab = (ABean) bf.getBean("aBean");
//            ab.doSomthing();
//        }

        System.out.println("��̬����������ʽ------------");
        for (int i = 0; i < 3; i++) {
            ABean ab = (ABean) bf.getBean("staticABean");
            ab.doSomthing();
        }

        System.out.println("����������ʽ------------");
//        for (int i = 0; i < 3; i++) {
//            ABean ab = (ABean) bf.getBean("factoryAbean");
//            ab.doSomthing();
//        }

        //����
     //   bf.close();
    }
}

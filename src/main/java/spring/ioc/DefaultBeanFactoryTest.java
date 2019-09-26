package spring.ioc;

import org.junit.AfterClass;
import org.junit.Test;

/**
 * @Title DefaultBeanFactoryTest
 * @Description TODO
 * @Author liuxi58
 * @Date 2019/9/26 19:27
 **/
public class DefaultBeanFactoryTest {

    static DefaultBeanFactory bf = new DefaultBeanFactory();
    @Test
    public void testRegist() throws Exception{
        GenericBeanDefinition bd = new GenericBeanDefinition();
        bd.setBeanClass(ABean.class);
        bd.setScope(BeanDefinition.SCOPE_SINGLETION);
        bd.setInitMethodName("init");
        bd.setDestroyMethodName("destroy");
        bf.registerBeanDefinition("aBean",bd);
    }
    //��̬���������ķ�ʽ����beanʵ��
    @Test
    public void testRegistStaticFactoryMethod() throws Exception {
        //����bean����
        GenericBeanDefinition bd = new GenericBeanDefinition();
        //���ù���bean������
        bd.setBeanClass(ABeanFactory.class);
        //���ù���������
        bd.setFactoryMethodName("getABean");
        //��bean����ע�ᵽbean����DefaultBeanFactory bf
        bf.registerBeanDefinition("staticAbean", bd);
    }
    @Test
    public void testRegistFactoryMethod() throws Exception{
        GenericBeanDefinition bd = new GenericBeanDefinition();
        bd.setBeanClass(ABeanFactory.class);
        String fbname = "factory";
        bf.registerBeanDefinition(fbname,bd);
        bd = new GenericBeanDefinition();
        bd.setFactoryBeanName(fbname);
        bd.setFactoryMethodName("getABean2");
        bd.setScope(BeanDefinition.SCOPE_PROTOTYPE);
        bf.registerBeanDefinition("factoryAbean",bd);
    }
    @AfterClass
    public static void testGetBean() throws Exception{
        System.out.println("------------");
        for (int i = 0; i < 3; i++) {
            ABean ab = (ABean) bf.getBean("aBean");
            ab.doSomthing();
        }
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
           ABean ab = (ABean) bf.getBean("staticAbean");
           ab.doSomthing();
        }
        System.out.println("����������ʽ------------");
        for (int i = 0; i < 3; i++) {
            ABean ab = (ABean) bf.getBean("factoryAbean");
            ab.doSomthing();
        }

        //����
        bf.close();
    }
}

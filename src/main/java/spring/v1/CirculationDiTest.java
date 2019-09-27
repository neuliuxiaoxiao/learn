package spring.v1;

import org.junit.Test;
import spring.beans.BeanReference;
import spring.beans.GenericBeanDefinition;
import spring.beans.PreBuildBeanFactory;
import spring.samples.DBean;
import spring.samples.EBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title CirculationDiTest
 * @Description —≠ª∑“¿¿µ≤‚ ‘
 * @Author liuxi58
 * @Date 2019/9/27 16:37
 **/
public class CirculationDiTest {

    static PreBuildBeanFactory bf = new PreBuildBeanFactory();
    //DBean“¿¿µEBean,EBean”÷“¿¿µDBean ≈◊“Ï≥£
    @Test
    public void testCirculationDI() throws Exception {
        GenericBeanDefinition bd = new GenericBeanDefinition();
        bd.setBeanClass(DBean.class);
        List<Object> args = new ArrayList<>();
        args.add(new BeanReference("ebean"));
        bd.setConstructorArgumentValues(args);
        bf.registerBeanDefinition("dbean", bd);

        bd = new GenericBeanDefinition();
        bd.setBeanClass(EBean.class);
        args = new ArrayList<>();
        args.add(new BeanReference("dbean"));
        bd.setConstructorArgumentValues(args);
        bf.registerBeanDefinition("ebean", bd);

        bf.preInstantiateSingletons();
    }
}

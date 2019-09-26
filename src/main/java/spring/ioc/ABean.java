package spring.ioc;

/**
 * @Title ABean
 * @Description TODO
 * @Author liuxi58
 * @Date 2019/9/26 19:26
 **/
public class ABean {
    public void doSomthing() {
        System.out.println(System.currentTimeMillis() + " " + this);
    }

    public void init() {
        System.out.println("ABean.init() ִ����");
    }

    public void destroy() {
        System.out.println("ABean.destroy() ִ����");
    }
}

package spring.samples;

/**
 * @Title ABean
 * @Description ��Ҫ������beanʵ��ABean
 * @Author liuxi58
 * @Date 2019/9/27 12:27
 **/
public class ABean {
    private String name;

    private CBean cb;

    public ABean(String name, CBean cb) {
        super();
        this.name = name;
        this.cb = cb;
        System.out.println("�����˺���CBean�����Ĺ��췽��");
    }

    public ABean(String name, CCBean cb) {
        super();
        this.name = name;
        this.cb = cb;
        System.out.println("�����˺���CCBean�����Ĺ��췽��");
    }

    public ABean(CBean cb) {
        super();
        this.cb = cb;
    }

    public void doSomthing() {
        System.out.println(System.currentTimeMillis() + " " + this.name + " cb.name=" + this.cb.getName());
    }

    public void init() {
        System.out.println("ABean.init() ִ����");
    }

    public void destroy() {
        System.out.println("ABean.destroy() ִ����");
    }
}

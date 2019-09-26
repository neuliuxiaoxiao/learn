package design.adapter;

/**
 * @Title SomeAdapter
 * @Description TODO
 * @Author liuxi58
 * @Date 2019/9/11 14:18
 **/
public class SomeAdapter implements Target {

    private SomeThing s;
    public SomeAdapter(SomeThing s){
        this.s=s;
    }

    @Override
    public void method1() {
        System.out.println("自己实现");
    }

    @Override
    public void method2() {
    s.method2();
    }

    @Override
    public void method3() {
    s.method3();
    }
}

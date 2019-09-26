package design.proxy;

/**
 * @Title NewFrank
 * @Description TODO
 * @Author liuxi58
 * @Date 2019/9/26 8:37
 **/
public class NewFrank extends Frank {

    @Override
    protected void login() {
        System.out.println("new frank login");
    }
}

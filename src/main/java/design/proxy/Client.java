package design.proxy;

/**
 * @Title Client
 * @Description TODO
 * @Author liuxi58
 * @Date 2019/9/26 8:17
 **/
public class Client {

    public static void main(String[] args) {
//        ISurf surf =  ProxyFactory.getStaticProxy("design.proxy.NewSurf");
//        surf.play("init");
//        surf.buy();

//        ISurf surf = (ISurf) ProxyFactory.getJdkProxy("design.proxy.NewSurf");
//        surf.play("123");
//        surf.buy();

//        ISurf surf = (ISurf) ProxyFactory.getCglibProxy("design.proxy.NewSurf");
//        surf.play("123");
//        surf.buy();

//        CalcuteSurf.calcute(new OldSurf(),"hhhh");

        Frank frank = new NewFrank();
        frank.muban();
    }
}

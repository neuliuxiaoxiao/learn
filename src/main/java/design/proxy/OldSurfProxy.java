package design.proxy;

/**
 * @Title OldSurfProxy
 * @Description TODO
 * @Author liuxi58
 * @Date 2019/9/26 8:15
 **/
public class OldSurfProxy implements ISurf {

    private ISurf target;

    public OldSurfProxy(ISurf target) {
        this.target = target;
    }

    @Override
    public String play(String name) {
        System.out.println("进入OldSurfProxy-play");
        return target.play(name);
    }

    @Override
    public String buy() {
        System.out.println("进入OldSurfProxy-buy");
        return target.buy();
    }
}

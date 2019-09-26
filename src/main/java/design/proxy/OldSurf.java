package design.proxy;

/**
 * @Title OldSurf
 * @Description TODO
 * @Author liuxi58
 * @Date 2019/9/26 8:14
 **/
public class OldSurf implements ISurf {

    @Override
    public String play(String name) {
        System.out.println("old - play");
        return name;
    }

    @Override
    public String buy() {
        System.out.println("old - buy");
        return "buy";
    }
}

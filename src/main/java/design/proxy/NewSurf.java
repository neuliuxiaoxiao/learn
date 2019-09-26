package design.proxy;

/**
 * @Title NewSurf
 * @Description TODO
 * @Author liuxi58
 * @Date 2019/9/26 8:18
 **/
public class NewSurf implements ISurf {

    @Override
    public String play(String name) {
        System.out.println("new - play");
        return name;
    }

    @Override
    public String buy() {
        System.out.println("new - buy");
        return "buy";
    }
}

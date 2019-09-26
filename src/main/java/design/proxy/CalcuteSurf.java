package design.proxy;

/**
 * @Title CalcuteSurf
 * @Description TODO
 * @Author liuxi58
 * @Date 2019/9/26 8:34
 **/
public class CalcuteSurf {

    public static String calcute(ISurf o,String name ){
        return o.play(name);
    }
}

package spring.ioc;

/**
 * @Title ABeanFactory
 * @Description TODO
 * @Author liuxi58
 * @Date 2019/9/26 19:26
 **/
public class ABeanFactory {

    public static ABean getABean(){
        return new ABean();
    }
    public ABean getABean2(){
        return  new ABean();
    }
}

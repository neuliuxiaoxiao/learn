package spring.samples;

/**
 * @Title ABeanFactory
 * @Description 创建bean实例ABean的工厂ABeanFactory
 * @Author liuxi58
 * @Date 2019/9/27 12:27
 **/
public class ABeanFactory {
    //静态工厂方式
    public static ABean getABean(String name, CBean cb) {
        return new ABean(name, cb);
    }

    public ABean getABean2(String name, CBean cb) {
        return new ABean(name, cb);
    }
}

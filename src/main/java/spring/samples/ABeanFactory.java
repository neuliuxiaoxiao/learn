package spring.samples;

/**
 * @Title ABeanFactory
 * @Description ����beanʵ��ABean�Ĺ���ABeanFactory
 * @Author liuxi58
 * @Date 2019/9/27 12:27
 **/
public class ABeanFactory {
    //��̬������ʽ
    public static ABean getABean(String name, CBean cb) {
        return new ABean(name, cb);
    }

    public ABean getABean2(String name, CBean cb) {
        return new ABean(name, cb);
    }
}

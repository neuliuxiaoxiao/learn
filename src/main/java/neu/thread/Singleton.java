package neu.thread;

/**
 * @Title Singleton
 * @Description TODO
 * @Author liuxi58
 * @Date 2019/9/10 17:42
 **/
public class Singleton {

    private volatile static Singleton singleton;

    private Singleton(){}

    public static Singleton getInstance(){
        if (singleton==null){
            synchronized (Singleton.class){
                if (singleton==null) {
                    singleton=new Singleton();
                }
            }
        }
        return  singleton;
    }


}

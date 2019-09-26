package design.observe;

/**
 * @Title Observer
 * @Description TODO
 * @Author liuxi58
 * @Date 2019/9/11 15:01
 **/
public abstract class Observer {

    protected Subject subject;

    public abstract void update();
}

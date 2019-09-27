package spring.aop;

/**
 * @Title AopProxy
 * @Description JDK动态代理和cglib动态代理抽象出公共部分的接口去获取代理对象
 * @Author liuxi58
 * @Date 2019/9/27 19:31
 **/
public interface AopProxy {

    //获取代理对象
    Object getProxy();
    //通过类加载器获取代理对象
    Object getProxy(ClassLoader classLoader);
}

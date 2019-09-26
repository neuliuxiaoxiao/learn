package design.proxy;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Title CglibProxy
 * @Description TODO
 * @Author liuxi58
 * @Date 2019/9/26 8:28
 **/
public class CglibProxy implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        if (method.getName().equals("buy")) {
            System.out.println("cglib method buy");
        } else {
            System.out.println("cglib method play");
        }
        Object result = methodProxy.invokeSuper(o, objects);
        return result;
    }
}

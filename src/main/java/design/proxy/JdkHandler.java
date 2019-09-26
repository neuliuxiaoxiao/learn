package design.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Title JdkHandler
 * @Description TODO
 * @Author liuxi58
 * @Date 2019/9/26 8:22
 **/
public class JdkHandler implements InvocationHandler {

    private Object target ;

    public JdkHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equals("buy")){
            System.out.println("jdk method buy");
        }else {
            System.out.println("jdk method play");
        }
        Object result = method.invoke(target,args);
        return result;
    }
}

package design.proxy;


import org.springframework.cglib.proxy.Enhancer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @Title ProxyFactory
 * @Description TODO
 * @Author liuxi58
 * @Date 2019/9/26 8:17
 **/
public class ProxyFactory {

    public static ISurf getStaticProxy(String classname) {
        try {
            Class cl = Class.forName(classname);
            ISurf surf = (ISurf) cl.newInstance();
            return new OldSurfProxy(surf);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object getJdkProxy(String classname) {
        Class cl = null;
        try {
            cl = Class.forName(classname);
            ISurf surf = (ISurf) cl.newInstance();
            InvocationHandler handler = new JdkHandler(surf);
            return Proxy.newProxyInstance(surf.getClass().getClassLoader(), surf.getClass().getInterfaces(), handler);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object getCglibProxy(String classname) {
        Class cl = null;
        try {
            cl = Class.forName(classname);
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(cl);
            enhancer.setCallback(new CglibProxy());
            Object result = enhancer.create();
            return result;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}

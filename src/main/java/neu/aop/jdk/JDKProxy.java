package neu.aop.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import neu.aop.UserDao;
import neu.aop.UserDaoImp;

public class JDKProxy implements InvocationHandler {

    /**
     * 要被代理的目标对象
     */
    private UserDao target;

    public JDKProxy(UserDao target){
        this.target=target;
    }

    /**
     * 创建代理类
     * @return
     */
    public UserDao createProxy(){
        return (UserDao) Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),this);
    }



    /**
     * 调用被代理类(目标对象)的任意方法都会触发invoke方法
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if("addUser".equals(method.getName())){
            // 记录日志:
            System.out.println("日志记录======");

            //调用目标对象的方法
            Object result = method.invoke(target, args);
            return result;
        }
        return method.invoke(target, args);
    }
    public static void main(String args[]){
    	UserDao a=new UserDaoImp();
        //创建JDK代理
        JDKProxy jdkProxy=new JDKProxy(a);
        //创建代理对象
        UserDao proxy=jdkProxy.createProxy();
        //执行代理对象方法
        proxy.addUser();
    }
}
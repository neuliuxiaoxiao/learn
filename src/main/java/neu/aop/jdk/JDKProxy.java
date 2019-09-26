package neu.aop.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import neu.aop.UserDao;
import neu.aop.UserDaoImp;

public class JDKProxy implements InvocationHandler {

    /**
     * Ҫ�������Ŀ�����
     */
    private UserDao target;

    public JDKProxy(UserDao target){
        this.target=target;
    }

    /**
     * ����������
     * @return
     */
    public UserDao createProxy(){
        return (UserDao) Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),this);
    }



    /**
     * ���ñ�������(Ŀ�����)�����ⷽ�����ᴥ��invoke����
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if("addUser".equals(method.getName())){
            // ��¼��־:
            System.out.println("��־��¼======");

            //����Ŀ�����ķ���
            Object result = method.invoke(target, args);
            return result;
        }
        return method.invoke(target, args);
    }
    public static void main(String args[]){
    	UserDao a=new UserDaoImp();
        //����JDK����
        JDKProxy jdkProxy=new JDKProxy(a);
        //�����������
        UserDao proxy=jdkProxy.createProxy();
        //ִ�д�����󷽷�
        proxy.addUser();
    }
}
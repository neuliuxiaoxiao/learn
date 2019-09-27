package spring.aop;

import spring.aop.advice.AfterReturningAdvice;
import spring.aop.advice.MethodBeforeAdvice;
import spring.aop.advice.MethodInterceptor;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @Title AopAdviceChainInvocation
 * @Description ������ʽִ����ǿ
 * @Author liuxi58
 * @Date 2019/9/27 20:03
 **/
public class AopAdviceChainInvocation {
    private static Method invokeMethod;
    static {
        try {
            invokeMethod = AopAdviceChainInvocation.class.getMethod("invoke",null);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
    private Object proxy;
    private Object target;
    private Method method;
    private Object[] args;
    private List<Object> advices;

    public AopAdviceChainInvocation(Object proxy, Object target, Method method, Object[] args, List<Object> advices) {
        super();
        this.proxy = proxy;
        this.target = target;
        this.method = method;
        this.args = args;
        this.advices = advices;
    }
    //������ִ�м�¼������
    private int i =0;
    public Object invoke() throws Throwable{
        if (i<this.advices.size()){
            Object advice = this.advices.get(i++);
            if (advice instanceof MethodBeforeAdvice){
                //ִ��ǰ����ǿ
                ((MethodBeforeAdvice) advice).before(method,args,target);
            }else if (advice instanceof MethodInterceptor){
                // ִ�л�����ǿ���쳣������ǿ��ע����������method �� ���� ��invoke������������
                return ((MethodInterceptor) advice).invoke(invokeMethod,null,this);
            }else if (advice instanceof AfterReturningAdvice){
                // ���Ǻ�����ǿʱ���ȵõõ��������ִ�к�����ǿ�߼�
                Object returnValue = this.invoke();
                ((AfterReturningAdvice) advice).afterReturning(returnValue,method,args,target);
                return returnValue;
            }
            return this.invoke();
        }else {
            return method.invoke(target,args);
        }
    }
}

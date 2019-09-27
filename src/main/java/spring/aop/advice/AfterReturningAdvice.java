package spring.aop.advice;

import java.lang.reflect.Method;

/**
 * @Title AfterReturningAdvice
 * @Description 后置增强接口
 * @Author liuxi58
 * @Date 2019/9/27 17:30
 **/
public interface AfterReturningAdvice extends Advice {

    /**
     * @param returnValue 被增强的方法的返回值
     * @param method 被增强的方法
     * @param args 被增强的方法的参数
     * @param target 被增强的目标对象(被增强的方法所在的类)
     * @throws Throwable 异常
     */
    void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable;
}

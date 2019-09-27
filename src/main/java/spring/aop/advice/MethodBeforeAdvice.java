package spring.aop.advice;

import java.lang.reflect.Method;

/**
 * @Title MethodBeforeAdvice
 * @Description 前置增强接口
 * @Author liuxi58
 * @Date 2019/9/27 16:56
 **/
public interface MethodBeforeAdvice extends Advice {

    /**
     * 实现该方法进行前置增强
     * @param method 被增强的方法
     * @param args 被增强的方法的参数
     * @param target 被增强的目标对象(被增强的方法所在的类)
     * @throws Throwable 异常
     */
    void before(Method method, Object[] args, Object target) throws Throwable;

}

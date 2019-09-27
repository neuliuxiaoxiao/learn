package spring.aop.advice;

import java.lang.reflect.Method;

/**
 * @Title MethodInterceptor
 * @Description 对方法进行环绕（前置、后置）增强、异常处理增强接口
 * @Author liuxi58
 * @Date 2019/9/27 17:32
 **/
public interface MethodInterceptor extends Advice {

    /**
     * @param method 被增强的方法
     * @param args 被增强的方法的参数
     * @param target 被增强的目标对象
     * @return 被增强的方法的返回值
     * @throws Throwable
     */
    Object invoke(Method method, Object[] args, Object target) throws Throwable;
}

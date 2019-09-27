package spring.aop.pointcut;

import java.lang.reflect.Method;

/**
 * @Title Pointcut
 * @Description Pointcut标准接口
 * @Author liuxi58
 * @Date 2019/9/27 17:46
 **/
public interface Pointcut {
    //匹配类
    boolean matchsClass(Class<?> targetClass);
    //匹配方法
    boolean matchsMethod(Method method,Class<?> targetClass);
}

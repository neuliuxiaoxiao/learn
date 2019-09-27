package spring.aop.pointcut;

import java.lang.reflect.Method;

/**
 * @Title Pointcut
 * @Description Pointcut��׼�ӿ�
 * @Author liuxi58
 * @Date 2019/9/27 17:46
 **/
public interface Pointcut {
    //ƥ����
    boolean matchsClass(Class<?> targetClass);
    //ƥ�䷽��
    boolean matchsMethod(Method method,Class<?> targetClass);
}

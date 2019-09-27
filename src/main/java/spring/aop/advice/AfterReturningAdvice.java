package spring.aop.advice;

import java.lang.reflect.Method;

/**
 * @Title AfterReturningAdvice
 * @Description ������ǿ�ӿ�
 * @Author liuxi58
 * @Date 2019/9/27 17:30
 **/
public interface AfterReturningAdvice extends Advice {

    /**
     * @param returnValue ����ǿ�ķ����ķ���ֵ
     * @param method ����ǿ�ķ���
     * @param args ����ǿ�ķ����Ĳ���
     * @param target ����ǿ��Ŀ�����(����ǿ�ķ������ڵ���)
     * @throws Throwable �쳣
     */
    void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable;
}

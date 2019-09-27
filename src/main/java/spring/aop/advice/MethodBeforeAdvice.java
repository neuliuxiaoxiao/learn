package spring.aop.advice;

import java.lang.reflect.Method;

/**
 * @Title MethodBeforeAdvice
 * @Description ǰ����ǿ�ӿ�
 * @Author liuxi58
 * @Date 2019/9/27 16:56
 **/
public interface MethodBeforeAdvice extends Advice {

    /**
     * ʵ�ָ÷�������ǰ����ǿ
     * @param method ����ǿ�ķ���
     * @param args ����ǿ�ķ����Ĳ���
     * @param target ����ǿ��Ŀ�����(����ǿ�ķ������ڵ���)
     * @throws Throwable �쳣
     */
    void before(Method method, Object[] args, Object target) throws Throwable;

}

package spring.aop.advice;

import java.lang.reflect.Method;

/**
 * @Title MethodInterceptor
 * @Description �Է������л��ƣ�ǰ�á����ã���ǿ���쳣������ǿ�ӿ�
 * @Author liuxi58
 * @Date 2019/9/27 17:32
 **/
public interface MethodInterceptor extends Advice {

    /**
     * @param method ����ǿ�ķ���
     * @param args ����ǿ�ķ����Ĳ���
     * @param target ����ǿ��Ŀ�����
     * @return ����ǿ�ķ����ķ���ֵ
     * @throws Throwable
     */
    Object invoke(Method method, Object[] args, Object target) throws Throwable;
}

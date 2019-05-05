package neu.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class MyAspect {

    /**
     * ǰ��֪ͨ
     */
    @Before("execution(* neu.aop.UserDao.addUser(..))")
    public void before(){
        System.out.println("ǰ��֪ͨ....");
    }

    /**
     * ����֪ͨ
     * returnVal,�е㷽��ִ�к�ķ���ֵ
     */
    @AfterReturning(value="execution(* neu.aop.UserDao.addUser(..))",returning = "returnVal")
    public void AfterReturning(Object returnVal){
        System.out.println("����֪ͨ...."+returnVal);
    }


    /**
     * ����֪ͨ
     * @param joinPoint ������ִ���е����
     * @return
     * @throws Throwable
     */
    @Around("execution(* neu.aop.UserDao.addUser(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("����֪ͨǰ....");
        Object obj= (Object) joinPoint.proceed();
        System.out.println("����֪ͨ��....");
        return obj;
    }

    /**
     * �׳�֪ͨ
     * @param e
     */
    @AfterThrowing(value="execution(* neu.aop.UserDao.addUser(..))",throwing = "e")
    public void afterThrowable(Throwable e){
        System.out.println("�����쳣:msg="+e.getMessage());
    }

    /**
     * ����ʲô����¶���ִ�еķ���
     */
    @After(value="execution(* neu.aop.UserDao.addUser(..))")
    public void after(){
        System.out.println("����֪ͨ....");
    }
}

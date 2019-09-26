package neu.aop;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.Ordered;

@Aspect
public class AspectOne implements Ordered {

    /**
     * Pointcut�����е㺯��
     */
    @Pointcut("execution(* neu.aop.UserDao.deleteUser(..))")
    private void myPointcut(){}

    @Before("myPointcut()")
    public void beforeOne(){
        System.out.println("ǰ��֪ͨ....ִ��˳��1");
    }

    @Before("myPointcut()")
    public void beforeTwo(){
        System.out.println("ǰ��֪ͨ....ִ��˳��2");
    }

    @AfterReturning(value = "myPointcut()")
    public void AfterReturningThree(){
        System.out.println("����֪ͨ....ִ��˳��3");
    }

    @AfterReturning(value = "myPointcut()")
    public void AfterReturningFour(){
        System.out.println("����֪ͨ....ִ��˳��4");
    }

	@Override
    public int getOrder() {
		return 0;
	}
}

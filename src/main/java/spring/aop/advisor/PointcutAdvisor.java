package spring.aop.advisor;

import spring.aop.pointcut.Pointcut;

/**
 * @Title PointcutAdvisor
 * @Description ����������֪ͨ��ʵ��
 * @Author liuxi58
 * @Date 2019/9/27 17:56
 **/
public interface  PointcutAdvisor  extends  Advisor{

    Pointcut getPointcut();
}

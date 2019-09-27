package spring.aop.advisor;

import spring.aop.pointcut.AspectJExpressionPointcut;
import spring.aop.pointcut.Pointcut;

/**
 * @Title AspectJPointcutAdvisor
 * @Description ����AspectJ������֪ͨ��ʵ��     �û����һ������,����Advice(������ǿ)��Pointcut(�����)
 * @Author liuxi58
 * @Date 2019/9/27 17:57
 **/
public class AspectJPointcutAdvisor implements PointcutAdvisor {
//    �û����õ�advice��bean������
    private String adviceBeanName;
//    �������ʽ
    private String expression;
//    AspectJ���ʽ��������
    private AspectJExpressionPointcut pointcut;

    public AspectJPointcutAdvisor(String adviceBeanName, String expression) {
        this.adviceBeanName = adviceBeanName;
        this.expression = expression;
        this.pointcut = new AspectJExpressionPointcut(this.expression);
    }

    @Override
    public Pointcut getPointcut() {
        return null;
    }

    @Override
    public String getAdviceBeanName() {
        return null;
    }

    @Override
    public String getExpression() {
        return null;
    }
}

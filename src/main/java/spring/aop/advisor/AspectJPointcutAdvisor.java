package spring.aop.advisor;

import spring.aop.pointcut.AspectJExpressionPointcut;
import spring.aop.pointcut.Pointcut;

/**
 * @Title AspectJPointcutAdvisor
 * @Description 基于AspectJ切入点的通知者实现     用户配的一个切面,包含Advice(功能增强)和Pointcut(切入点)
 * @Author liuxi58
 * @Date 2019/9/27 17:57
 **/
public class AspectJPointcutAdvisor implements PointcutAdvisor {
//    用户配置的advice的bean的名字
    private String adviceBeanName;
//    切入点表达式
    private String expression;
//    AspectJ表达式切入点对象
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

package spring.aop.pointcut;

import org.aspectj.weaver.tools.PointcutExpression;
import org.aspectj.weaver.tools.PointcutParameter;
import org.aspectj.weaver.tools.PointcutParser;
import org.aspectj.weaver.tools.ShadowMatch;

import java.lang.reflect.Method;

/**
 * @Title AspectJExpressionPointcut
 * @Description AspectJ���ʽ��Pointcut
 * @Author liuxi58
 * @Date 2019/9/27 17:51
 **/
public class AspectJExpressionPointcut implements Pointcut {

    //����е������
    private static PointcutParser pp = PointcutParser.getPointcutParserSupportingAllPrimitivesAndUsingContextClassloaderForResolution();
    //���ʽ
    private String expression;
    //Pointcut���ʽ����
    private PointcutExpression pe;

    public AspectJExpressionPointcut(String expression) {
        super();
        this.expression = expression;
        //�������ʽ�õ�org.aspectj.weaver.tools.PointcutExpression
        pe = pp.parsePointcutExpression(expression);
    }
    //ƥ����  ��PointcutExpressionƥ���࣬ƥ����ʱ��׼���������ͨ��ƥ�䷽������ȷƥ��
    @Override
    public boolean matchsClass(Class<?> targetClass) {
        return pe.couldMatchJoinPointsInType(targetClass);
    }
    //ƥ�䷽��  ��PointcutExpressionƥ�䷽��������ʵ�־�ȷƥ��
    @Override
    public boolean matchsMethod(Method method, Class<?> targetClass) {
        ShadowMatch sm = pe.matchesAdviceExecution(method);
        return sm.alwaysMatches();
    }
    public String getExpression(){
        return expression;
    }
}

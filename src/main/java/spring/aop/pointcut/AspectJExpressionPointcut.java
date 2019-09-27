package spring.aop.pointcut;

import org.aspectj.weaver.tools.PointcutExpression;
import org.aspectj.weaver.tools.PointcutParameter;
import org.aspectj.weaver.tools.PointcutParser;
import org.aspectj.weaver.tools.ShadowMatch;

import java.lang.reflect.Method;

/**
 * @Title AspectJExpressionPointcut
 * @Description AspectJ表达式的Pointcut
 * @Author liuxi58
 * @Date 2019/9/27 17:51
 **/
public class AspectJExpressionPointcut implements Pointcut {

    //获得切点解析器
    private static PointcutParser pp = PointcutParser.getPointcutParserSupportingAllPrimitivesAndUsingContextClassloaderForResolution();
    //表达式
    private String expression;
    //Pointcut表达式对象
    private PointcutExpression pe;

    public AspectJExpressionPointcut(String expression) {
        super();
        this.expression = expression;
        //解析表达式得到org.aspectj.weaver.tools.PointcutExpression
        pe = pp.parsePointcutExpression(expression);
    }
    //匹配类  用PointcutExpression匹配类，匹配有时候不准，后面可以通过匹配方法来精确匹配
    @Override
    public boolean matchsClass(Class<?> targetClass) {
        return pe.couldMatchJoinPointsInType(targetClass);
    }
    //匹配方法  用PointcutExpression匹配方法，可以实现精确匹配
    @Override
    public boolean matchsMethod(Method method, Class<?> targetClass) {
        ShadowMatch sm = pe.matchesAdviceExecution(method);
        return sm.alwaysMatches();
    }
    public String getExpression(){
        return expression;
    }
}

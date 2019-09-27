package spring.aop.advisor;

/**
 * @Title Advisor
 * @Description Advisor（通知者）接口
 * @Author liuxi58
 * @Date 2019/9/27 17:55
 **/
public interface Advisor {

    //获取配置的Advice的bean的名字
    String getAdviceBeanName();

    //获取切入点表达式
    String getExpression();

}

package spring.aop.advisor;

import java.util.List;

/**
 * @Title AdvisorRegistry
 * @Description Advisor注册接口
 * @Author liuxi58
 * @Date 2019/9/27 18:31
 **/
public interface AdvisorRegistry {

    //注册Advisor
    public void registAdvisor(Advisor advisor);
    //获取Advisor
    public List<Advisor> getAdvisors();
}

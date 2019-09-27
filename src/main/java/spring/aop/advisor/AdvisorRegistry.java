package spring.aop.advisor;

import java.util.List;

/**
 * @Title AdvisorRegistry
 * @Description Advisorע��ӿ�
 * @Author liuxi58
 * @Date 2019/9/27 18:31
 **/
public interface AdvisorRegistry {

    //ע��Advisor
    public void registAdvisor(Advisor advisor);
    //��ȡAdvisor
    public List<Advisor> getAdvisors();
}

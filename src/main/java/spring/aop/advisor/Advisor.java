package spring.aop.advisor;

/**
 * @Title Advisor
 * @Description Advisor��֪ͨ�ߣ��ӿ�
 * @Author liuxi58
 * @Date 2019/9/27 17:55
 **/
public interface Advisor {

    //��ȡ���õ�Advice��bean������
    String getAdviceBeanName();

    //��ȡ�������ʽ
    String getExpression();

}

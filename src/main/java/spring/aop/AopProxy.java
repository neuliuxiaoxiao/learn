package spring.aop;

/**
 * @Title AopProxy
 * @Description JDK��̬�����cglib��̬���������������ֵĽӿ�ȥ��ȡ�������
 * @Author liuxi58
 * @Date 2019/9/27 19:31
 **/
public interface AopProxy {

    //��ȡ�������
    Object getProxy();
    //ͨ�����������ȡ�������
    Object getProxy(ClassLoader classLoader);
}

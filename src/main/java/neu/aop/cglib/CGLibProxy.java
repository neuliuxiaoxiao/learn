package neu.aop.cglib;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

public class CGLibProxy implements MethodInterceptor {

	private Object target;

	public CGLibProxy(Object target) {
		super();
		this.target = target;
	}

	public Object createProxy() {
		// ʹ��CGLIB���ɴ���:
		// 1.������ǿ��ʵ��,��������������
		Enhancer enhancer = new Enhancer();
		// 2.���ñ��������ֽ��룬CGLIB�����ֽ������ɱ������������
		enhancer.setSuperclass(target.getClass());
		// 3.//���ûص���������һ����������
		enhancer.setCallback(this);
		// 4.��������:
		return (Object) enhancer.create();
	}

	/**
	 * �ص�����
	 * 
	 * @param proxy
	 *            �������
	 * @param method
	 *            ί���෽��
	 * @param args
	 *            ��������
	 * @param methodProxy
	 *            ÿ��������ķ�������Ӧһ��MethodProxy����
	 *            methodProxy.invokeSuper�������յ���ί����(Ŀ����)��ԭʼ����
	 * @return
	 * @throws Throwable
	 */
	@Override
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
		if ("execute".equals(method.getName())) {

			System.out.println("��־��¼==============");
			// ����Ŀ����ķ���
			Object obj = methodProxy.invokeSuper(proxy, args);
			return obj;
		}
		// �������Ҫ��ǿֱ��ִ��ԭ����
		return methodProxy.invokeSuper(proxy, args);
	}
	public static void main(String[] args) {
		A a = new A();
		CGLibProxy proxy = new CGLibProxy(a);
		A tt = (A) proxy.createProxy();
		tt.execute();
	}

}

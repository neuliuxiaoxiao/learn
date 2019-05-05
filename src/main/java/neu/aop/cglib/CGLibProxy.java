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
		// 使用CGLIB生成代理:
		// 1.声明增强类实例,用于生产代理类
		Enhancer enhancer = new Enhancer();
		// 2.设置被代理类字节码，CGLIB根据字节码生成被代理类的子类
		enhancer.setSuperclass(target.getClass());
		// 3.//设置回调函数，即一个方法拦截
		enhancer.setCallback(this);
		// 4.创建代理:
		return (Object) enhancer.create();
	}

	/**
	 * 回调函数
	 * 
	 * @param proxy
	 *            代理对象
	 * @param method
	 *            委托类方法
	 * @param args
	 *            方法参数
	 * @param methodProxy
	 *            每个被代理的方法都对应一个MethodProxy对象，
	 *            methodProxy.invokeSuper方法最终调用委托类(目标类)的原始方法
	 * @return
	 * @throws Throwable
	 */
	public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
		if ("execute".equals(method.getName())) {

			System.out.println("日志记录==============");
			// 调用目标类的方法
			Object obj = methodProxy.invokeSuper(proxy, args);
			return obj;
		}
		// 如果不需要增强直接执行原方法
		return methodProxy.invokeSuper(proxy, args);
	}
	public static void main(String[] args) {
		A a = new A();
		CGLibProxy proxy = new CGLibProxy(a);
		A tt = (A) proxy.createProxy();
		tt.execute();
	}

}

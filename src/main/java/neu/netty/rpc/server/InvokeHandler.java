package neu.netty.rpc.server;

import java.lang.reflect.Method;
import java.util.Set;

import org.reflections.Reflections;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class InvokeHandler extends ChannelInboundHandlerAdapter {

	private String getImplClassName(ClassInfo classInfo) throws ClassNotFoundException {
		// ���񷽽ӿں�ʵ�������ڵİ�·��
		String interfacePath = "neu.netty.rpc.server";
		int lastDot = classInfo.getClassName().lastIndexOf(".");
		String interfaceName = classInfo.getClassName().substring(lastDot);
		Class<?> superClass = Class.forName(interfacePath + interfaceName);
		// ��Ҫɨ��ĳ�����е�ĳ���ӿڵ�ʵ���������
		Reflections reflections = new Reflections(interfacePath);
		// �õ�ĳ�ӿ��µ�����ʵ����
		Set<?> ImplClassSet = reflections.getSubTypesOf(superClass);
		if (ImplClassSet.size() == 0) {
			System.out.println("δ�ҵ�ʵ����");
			return null;
		} else if (ImplClassSet.size() > 1) {
			System.out.println("�ҵ����ʵ���࣬δ��ȷʹ����һ��");
			return null;
		} else {
			// �Ѽ���ת��Ϊ����
			Class[] classes = ImplClassSet.toArray(new Class[0]);
			return classes[0].getName();// �õ�ʵ���������
		}
	}

	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ClassInfo classInfo = (ClassInfo) msg;
		Object clazz = Class.forName(getImplClassName(classInfo)).newInstance();
		Method method = clazz.getClass().getMethod(classInfo.getMethodName(), classInfo.getTypes());
		// ͨ���������ʵ����ķ���
		Object result = method.invoke(clazz, classInfo.getObjects());
		ctx.writeAndFlush(result);
	}
}

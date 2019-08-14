package neu.jvm;

/**
 * 
 * 引用计数器算法 给每一个对象设置一个引用计数器，每当有一个地方引用 这个对象的时候，计数器就加1，与之相反，每当引用失效的时候就减1。
 * 优点：实现简单、性能高。
 * 
 * 缺点：增减处理频繁消耗cpu计算、计数器占用很多位浪费空间、最重要的缺点是无法解决循环引用的问题。
 *
 */
public class ReferenceDemo {

	public Object instance = null;
	private static final int _1MB = 1024 * 1024;
	private byte[] bigSize = new byte[10 * _1MB];

	public static void main(String[] args) {
		System.out.println("开始：" + Runtime.getRuntime().freeMemory() / (1024 * 1024));
		ReferenceDemo referenceDemo = new ReferenceDemo();
		ReferenceDemo referenceDemo2 = new ReferenceDemo();
		referenceDemo.instance=referenceDemo2;
		referenceDemo2.instance=referenceDemo;
		System.out.println("运行：" + Runtime.getRuntime().freeMemory() / (1024 * 1024));
		referenceDemo=null;
		referenceDemo=null;
		System.gc();
		System.out.println("结束：" + Runtime.getRuntime().freeMemory() / (1024 * 1024));

	}
}

package neu.thread;

public class ThreadLocalTest2 {

	public static ThreadLocal<String> threadLocal = new InheritableThreadLocal<String>();
	public static void main(String[] args) {
		threadLocal.set("hello");
		new Thread(new Runnable() {
			public void run() {
				System.out.println(threadLocal.get());
			}
		}).start();
		System.out.println("main:"+threadLocal.get());
	}
}

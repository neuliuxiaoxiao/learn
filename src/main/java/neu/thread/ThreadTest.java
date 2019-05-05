package neu.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class ThreadTest {

	//方式1 Thread
	public static class MyThread extends Thread{
		public void run() {
			System.out.println("I'm a child thread"+this.getName());
		}
	}
	//方式2 Runnable
	public static class RunnableTask implements Runnable{
		public void run() {
			System.out.println("I'm a child thread"+Thread.currentThread().getName());
		}
	}
	//方式3 FutureTask
	public static class CallerTask implements Callable<String>{
		public String call() throws Exception {
			return "hello";
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		MyThread thread = new MyThread();
		
		thread.start();
		//调用wait方法需要先获取锁，不然会抛出IllegalMonitorStateException
		synchronized (thread) {
				thread.wait();
		}
		
		RunnableTask task  = new RunnableTask();
		new Thread(task).start();
		new Thread(task).start();
		
		FutureTask<String> futureTask = new FutureTask<>(new CallerTask());
		new Thread(futureTask).start();
		try {
			String result = futureTask.get();
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

package neu.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class ThreadTest {

	//鏂瑰紡1 Thread
	public static class MyThread extends Thread{
		@Override
        public void run() {
			System.out.println("I'm a child thread"+this.getName());
		}
	}
	//鏂瑰紡2 Runnable
	public static class RunnableTask implements Runnable{
		@Override
        public void run() {
			System.out.println("I'm a child thread"+Thread.currentThread().getName());
		}
	}
	//鏂瑰紡3 FutureTask
	public static class CallerTask implements Callable<String>{
		@Override
        public String call() throws Exception {
			return "hello";
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		MyThread thread = new MyThread();
		
		thread.start();
		//璋冪敤wait鏂规硶闇�瑕佸厛鑾峰彇閿侊紝涓嶇劧浼氭姏鍑篒llegalMonitorStateException
		synchronized (thread) {
				thread.wait();
		}
		
		RunnableTask task  = new RunnableTask();
		new Thread(task).start();
		new Thread(task).start();
		
		FutureTask<String> futureTask = new FutureTask<String>(new CallerTask());
		new Thread(futureTask).start();
		try {
			String result = futureTask.get();
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

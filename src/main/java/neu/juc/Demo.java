package neu.juc;

public class Demo {

	public static void main(String[] args) {
		Thread myThread= new MyThread("mythread");
		System.out.println(Thread.currentThread().getName());
		myThread.run();
		System.out.println(Thread.currentThread().getName());
		myThread.start();
	}
}
class MyThread extends Thread{
	public MyThread(String name){
		super(name);
	}
	public void run(){
		System.out.println(Thread.currentThread().getName()+" is running");
	}
}

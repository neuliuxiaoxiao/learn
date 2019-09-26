package neu.juc;

public class WaitTest {

	public static void main(String[] args) {
		ThreadA t1 = new ThreadA("t1");
		ThreadA t2 = new ThreadA("t2");
		synchronized (t1) {
			try {
				System.out.println(Thread.currentThread().getName()+" start t1");
				t1.start();
				t2.start();
				System.out.println(Thread.currentThread().getName()+ " wait");
				t1.wait();
				System.out.println(Thread.currentThread().getName()+" continue");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
class ThreadA extends Thread{
	public ThreadA(String name){
		super(name);
	}
	@Override
    public void run(){
		synchronized (this) {
			System.out.println(Thread.currentThread().getName()+ " call notify");
			this.notify();
		}
	}
}
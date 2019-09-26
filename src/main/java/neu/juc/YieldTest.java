package neu.juc;

public class YieldTest {

	public static void main(String[] args) {
		ThreadA1 t1 =new ThreadA1("t1");
		ThreadA1 t2 =new ThreadA1("t2");
		t1.start();
		t2.start();
	}
}

class ThreadA1 extends Thread{
	public ThreadA1(String name){
		super(name);
	}
	@Override
	public synchronized void run(){
		for (int i = 0; i < 10; i++) {
			System.out.printf("%s [%d]:%d\n", this.getName(), this.getPriority(), i); 
			if(i%4==0) {
                Thread.yield();
            }
		}
	}
}
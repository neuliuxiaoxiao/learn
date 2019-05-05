package neu.juc;

public class JoinTest {

	public static void main(String[] args) {
		try {
			ThreadA2 t1 = new ThreadA2("t1");
			t1.start();
			t1.join();
			System.out.printf("%s finish\n", Thread.currentThread().getName()); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	static class ThreadA2 extends Thread{
		public ThreadA2(String name){
			super(name);
		}
		public void run() {
			System.out.printf("%s start\n", this.getName()); 
            // 延时操作
            for(int i=0; i <1000000; i++)
               ;
            System.out.printf("%s finish\n", this.getName()); 
		}
	}
}

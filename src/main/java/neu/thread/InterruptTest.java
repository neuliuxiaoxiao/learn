package neu.thread;

public class InterruptTest {

	public static void main(String[] args) throws InterruptedException {
		Thread theadOne = new Thread(new Runnable() {
			@Override
            public void run() {
				for(;;){
					
				}
			}
		});
		theadOne.start();
		theadOne.interrupt();
		System.out.println("isInterrupted :"+theadOne.isInterrupted());
		System.out.println("isInterrupted :"+Thread.interrupted());
		System.out.println("isInterrupted :"+Thread.interrupted());
		System.out.println("isInterrupted :"+theadOne.isInterrupted());
		theadOne.join();
	}
}

package neu.thread;

public class DaemonTest {

	public static void main(String[] args) {
		Thread thread = new Thread(new Runnable() {
			@Override
            public void run() {
				for(;;){}
			}
		});
		thread.setDaemon(true);
		thread.start();
		System.out.println("over");
	}
}

package neu.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class AQSTest {

	public static void main(String[] args) {
		ReentrantLock lock = new ReentrantLock();
		Condition condition = lock.newCondition();
		lock.lock();
		try {
			System.out.println("begin wait");
			condition.await();
			System.out.println("end wait");
		} catch (Exception e) {
			e.printStackTrace();
		}
		lock.lock();
		try {
			System.out.println("begin signal ");
			condition.signal();
			System.out.println("end signal");
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			lock.unlock();
		}
	}
}

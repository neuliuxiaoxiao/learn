package neu.juc;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.locks.Condition;

public class NonLockTest {

	final static NonReenTrantLock lock = new NonReenTrantLock();
	final static Condition notFull = lock.newCondition();
	final static Condition notEmpty = lock.newCondition();
	final static Queue<String> queue = new LinkedBlockingDeque<String>();
	final static int queueSize=10;
	public static void main(String[] args) {
		Thread producer = new Thread(new Runnable() {
			
			public void run() {
				lock.lock();
				try {
					while(queue.size()==queueSize){
						notEmpty.await();
					}
					queue.add("ele");
					System.out.println("add ele");
					notFull.signalAll();
				} catch (Exception e) {
					e.printStackTrace();
				}finally {
					lock.unlock();
				}
			}
		});
		Thread consumer = new Thread(new Runnable() {
			public void run() {
				lock.lock();
				try {
					while(0==queue.size()){
						System.out.println("consumer wait");
						notFull.await();
					}
					String ele = queue.poll();
					System.out.println("remove ele");
					notEmpty.signalAll();
				} catch (Exception e) {
					e.printStackTrace();
				}finally {
					lock.unlock();
				}
			}
		});
		consumer.start();
		producer.start();
	}
}

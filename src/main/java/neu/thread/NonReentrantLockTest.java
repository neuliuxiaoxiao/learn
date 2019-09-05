package neu.thread;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;

public class NonReentrantLockTest {

	final static NonReentrantLock lock = new NonReentrantLock();
	final static Condition notFull = lock.newCondition();
	final static Condition notEmpty = lock.newCondition();
	final static LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<String>();
	final static int queueSize = 10;
	public static void main(String[] args) {
		Thread producer = new Thread(new Runnable() {
			
			@Override
			public void run() {
				lock.lock();
				try {
					while(queue.size()==queueSize){
						notEmpty.await();
					}
					queue.add("ele");
					notFull.signalAll();
				} catch (Exception e) {
					e.printStackTrace();
				}finally{
					lock.unlock();
				}
			}
		});
		Thread consumer = new Thread(new Runnable() {
			
			@Override
			public void run() {
				lock.lock();
				try {
					while(0==queue.size()){
						notFull.await();
					}
					String ele = queue.poll();
					notEmpty.signalAll();
				} catch (Exception e) {
					e.printStackTrace();
				}finally {
					lock.unlock();
				}
			}
		});
		producer.start();
		consumer.start();
	}
}

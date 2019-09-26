package neu.thread;

/*
 * 生产者消费者例子
 */
public class PCTest {
	
	public static void main(String[] args) {
		final Queue queue = new Queue();
		final int MAX_SIZE=3;
		new Thread(new Runnable() {
			@Override
            public void run() {
				//生产线程
				synchronized (queue) {
					//消费队列满，则等待队列空闲6
					while(queue.size==MAX_SIZE){
						try {
							//挂起当前线程，并释放通过同步块获取的queue上的锁，让消费者线程
							//可以获取该锁，然后获取队列里面的元素
							queue.wait();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					//空闲则生成元素 并通知消费者线程
					queue.add(1);
					queue.notifyAll();
				}
			}
		}).start();
		
		new Thread(new Runnable(){
			@Override
            public void run() {
				//消费者线程
				synchronized (queue) {
					//消费者队列为空
					while(queue.size==0){
						try {
							//挂起当前线程，并释放通过同步块获取的queue上的锁，让生产者线程
							//可以获取该锁，将生产元素放入队列
							System.out.println(Thread.currentThread().getName()+"等待");
							queue.wait();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					//消费元素，并通知唤醒生产者线程
					queue.take();
					queue.notifyAll();
				}
			}
		}).start();
	}
	
}
class Queue{
	int size =0;
	public void add(int size){
		this.size++;
		System.out.println(Thread.currentThread().getName()+"生产了");
	}
	public void take(){
		this.size--;
		System.out.println(Thread.currentThread().getName()+"消费了");
	}
	public int size(){
		return this.size;
	}
}
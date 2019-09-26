package neu.thread;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Title SemaphoreExample1
 * @Description TODO
 * @Author liuxi58
 * @Date 2019/9/10 16:50
 **/
public class SemaphoreExample1 {

    private static final int threadCount = 550;

    public static void main(String[] args) {

        ExecutorService threadPool = Executors.newFixedThreadPool(300);
        final Semaphore semaphore = new Semaphore(20);
        for (int i = 0; i <threadCount ; i++) {
            final  int threadnum =i;
            threadPool.execute(()->{
                try {
                    semaphore.acquire();
                    test(threadnum);
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        threadPool.shutdown();
        System.out.println("finish");
    }

    public static void test(int threadnum) throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("threadnum:" + threadnum);
        Thread.sleep(1000);
    }
}

package neu.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Title CountDownLatchExample1
 * @Description TODO
 * @Author liuxi58
 * @Date 2019/9/10 16:57
 **/
public class CountDownLatchExample1 {

    private static final int threaCount = 550;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(300);
        final CountDownLatch countDownLatch = new CountDownLatch(threaCount);
        for (int i = 0; i < threaCount; i++) {
            final int threadnum = i;
            threadPool.execute(() -> {
                try {
                    test(threadnum);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        threadPool.shutdown();
        System.out.println("finish");
    }

    public static void test(int threadnum) throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("threadnum:" + threadnum);
        Thread.sleep(1000);
    }
}

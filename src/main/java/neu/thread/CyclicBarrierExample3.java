package neu.thread;

import java.util.concurrent.*;

/**
 * @Title CyclicBarrierExample3
 * @Description TODO
 * @Author liuxi58
 * @Date 2019/9/10 17:31
 **/
public class CyclicBarrierExample3 {

    private static final int THREAD_COUNT = 550;
    private static final CyclicBarrier CYCLIC_BARRIER = new CyclicBarrier(5, () -> {
        System.out.println("当线程数达到之后，优先执行");
    });

    public static void main(String[] args) throws InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < THREAD_COUNT; i++) {
            final int threadNum = i;
            Thread.sleep(1000);
            threadPool.execute(() -> {
                try {
                    test(threadNum);
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        threadPool.shutdown();
    }

    public static void test(int threadnum) throws BrokenBarrierException, InterruptedException {
        System.out.println("threadnum:" + threadnum + "is ready");
        CYCLIC_BARRIER.await();
        System.out.println("threadnum:" + threadnum + "is finish");
    }

}

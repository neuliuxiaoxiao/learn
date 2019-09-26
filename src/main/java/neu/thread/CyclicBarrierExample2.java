package neu.thread;

import java.util.concurrent.*;

/**
 * @Title CyclicBarrierExample2
 * @Description TODO
 * @Author liuxi58
 * @Date 2019/9/10 17:02
 **/
public class CyclicBarrierExample2 {
    private static final int threadCount = 550;
    private static final CyclicBarrier cyclicBarrier = new CyclicBarrier(5);

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            threadPool.execute(() -> {
                test(threadNum);
            });
        }
        threadPool.shutdown();
    }
    public static void test(int threadnum){
        System.out.println("threadnum:"+threadnum+"is ready");
        try {
            cyclicBarrier.await(2000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("----cyclicbarrierexception----");
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
            System.out.println("----cyclicbarrierexception----");
        } catch (TimeoutException e) {
            e.printStackTrace();
            System.out.println("----cyclicbarrierexception----");
        }
        System.out.println("threadnum:"+threadnum+"is finish");
    }
}

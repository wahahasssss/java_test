import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/4/26
 * @Time 下午3:06
 */
public class ThreadPool {
    private volatile static AtomicInteger COUNT = new AtomicInteger(0);
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue queue = new ArrayBlockingQueue(1000);
        ThreadPoolExecutor pool = new ThreadPoolExecutor(100,100,
                1000, TimeUnit.MICROSECONDS,queue);
        for (int i = 0;i < 1000;i++){
            pool.execute(new Runnable() {
                @Override
                public void run() {
//                    Random random = new Random();
//                    try {
//                        Thread.sleep(1000 * random.nextInt(10));
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                    COUNT.incrementAndGet();
                    System.out.println(String.format("thread %s has done work...",Thread.currentThread().getName()));
                }
            });
        }


        pool.shutdown();
        pool.awaitTermination(Integer.MAX_VALUE,TimeUnit.DAYS);
        System.out.println(String.format("the thread finished work... COUNT values is %d",COUNT.get()));

    }
}

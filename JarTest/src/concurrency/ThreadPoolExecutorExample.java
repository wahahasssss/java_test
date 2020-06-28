package concurrency;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by CTWLPC on 2017/4/25.
 */
public class ThreadPoolExecutorExample implements Runnable{

    private static AtomicInteger counter = new AtomicInteger();
    private final int taskId;

    public int getTaskId() {
        return taskId;
    }

    public ThreadPoolExecutorExample(int taskId){
        this.taskId = taskId;
    }
    @Override
    public void run() {
        try {
            Thread.sleep(5000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        BlockingQueue<Runnable> queue = new LinkedBlockingDeque<Runnable>(10);
        ThreadFactory threadFactory = new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                int currentCount = counter.getAndIncrement();
                System.out.println("Create new Thread:"+currentCount);
                return new Thread(r,"MyThread" + currentCount);
            }
        };
        RejectedExecutionHandler rejectedExecutionHandler = new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                if (r instanceof ThreadPoolExecutorExample){
                    ThreadPoolExecutorExample example = (ThreadPoolExecutorExample)r;
                    System.out.println("Rejecting task with id" + example.getTaskId());
                }
            }
        };
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5,10,1,
                TimeUnit.SECONDS,queue,threadFactory,rejectedExecutionHandler);
        for (int i = 0;i<100;i++){
            executor.execute(new ThreadLocalExample(i));
        }
    }
}

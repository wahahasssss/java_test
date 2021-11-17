package concurrency;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by CTWLPC on 2017/4/25.
 */
public class ExecutorServiceExample implements Callable<Integer> {
    private static Random random = new Random(System.currentTimeMillis());

    @Override
    public Integer call() throws Exception {
        Thread.sleep(1000);
        return random.nextInt(100);
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Future<Integer>[] futures = new Future[5];
        for (int i = 0; i < futures.length; i++) {
            futures[i] = executorService.submit(new ExecutorServiceExample());
        }
        for (int i = 0; i < futures.length; i++) {
            Integer retVal = futures[i].get();
            System.out.println(retVal);
        }
        executorService.shutdown();
    }
}

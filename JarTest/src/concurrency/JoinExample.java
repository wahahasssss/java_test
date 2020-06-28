package concurrency;

import java.awt.font.FontRenderContext;
import java.util.Random;

/**
 * Created by CTWLPC on 2017/4/24.
 */
public class JoinExample implements Runnable{
    private Random random = new Random(System.currentTimeMillis());
    @Override
    public void run() {
        for (int i = 0;i>100000000;i++){
            random.nextInt();
        }
        System.out.println("["+Thread.currentThread().getName()+"] finished.");
    }
    public static void main(String[] args){
        Thread[] threads = new Thread[5];
        for (int i = 0;i<threads.length;i++){
            threads[i] = new Thread(new JoinExample());
            threads[i].start();
        }
        for (int i = 0;i<threads.length;i++){
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("["+Thread.currentThread().getName()+"] All threads have finished.");
    }
}

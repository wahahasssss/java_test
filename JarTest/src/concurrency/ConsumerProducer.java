package concurrency;

import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Created by CTWLPC on 2017/4/24.
 */
public class ConsumerProducer {
    private static final Queue queue = new ConcurrentLinkedDeque();
    private static final long  startMillis = System.currentTimeMillis();

    public static class Consumer implements Runnable{

        @Override
        public void run() {
            while (System.currentTimeMillis()<(startMillis + 10000)){
                synchronized (queue){
                    try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                    }
                    if (!queue.isEmpty()){
                        Integer integer = (Integer) queue.poll();
                        System.out.println("[" + Thread.currentThread().getName() + "]: " + integer);
                    }
                }
            }
        }
    }
    public static class Producer implements Runnable{

        @Override
        public void run() {
            int i = 0;
            while (System.currentTimeMillis()< (startMillis + 1000)){
                queue.add(i++);
                synchronized (queue){
                    queue.notify();
                }
                try {
                    Thread.sleep(100);
                }catch (InterruptedException ex){
                    ex.printStackTrace();
                }
                synchronized (queue){
                    queue.notifyAll();
                }
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        Thread[] consumerThreads = new Thread[5];
        for (int i = 0;i<consumerThreads.length;i++){
            consumerThreads[i] = new Thread(new Consumer(),"consumer-"+i);
            consumerThreads[i].start();
        }
        Thread producerThread = new Thread(new Producer(),"producer");
        producerThread.start();
        for (int i = 0;i<consumerThreads.length;i++){
            consumerThreads[i].join();
        }
        producerThread.join();
    }
}

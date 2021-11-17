package concurrency;

/**
 * Created by CTWLPC on 2017/4/24.
 */
public class MyRunnableThread implements Runnable {
    @Override
    public void run() {
        System.out.println("Executing thread" + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        Thread myThread = new Thread(new MyRunnableThread(), "MyRunnableThread");
        myThread.start();
    }
}

package concurrency;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by CTWLPC on 2017/4/24.
 */
public class MyThread extends Thread {
    public MyThread(String name) {
        super(name);
    }

    public void run() {
        System.out.println("Executing thread..." + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread("MyThread");
        myThread.start();

    }
}

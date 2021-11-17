/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2019/3/6
 * @Time 5:06 PM
 */
public class DamonThread {

    public static void main(String args[]) {
        Thread daemonThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("current time is " + System.currentTimeMillis());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        daemonThread.setDaemon(true);
        daemonThread.start();
        System.out.println("end...");

    }
}

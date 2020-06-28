package com.hdu;

import com.hdu.SecondFSM.FsmState;

import java.util.*;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/4/10
 * @Time 下午2:14
 */
public class FSMMain {

    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
        final SecondMachine machine = new SecondMachine();
        machine.updateState(machine.getOverLoadState());
        machine.switchProxy();
        Thread thread = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    machine.switchProxy();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();

        Thread thread1 = new Thread(new Runnable() {
            List<FsmState> arrayList = Arrays.asList(machine.getAbnormalState(), machine.getBannedState(),
                    machine.getNormalState(), machine.getOverLoadState(), machine.getSettingState());

            public void run() {
                while (true) {
                    Random random = new Random();
                    machine.updateState(arrayList.get(random.nextInt(5)));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread1.start();
        Scanner scanner = new Scanner(System.in);
        System.out.println("======>>>");
        String s = scanner.nextLine();
    }


}

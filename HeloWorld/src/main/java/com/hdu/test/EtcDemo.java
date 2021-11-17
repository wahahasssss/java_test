package com.hdu.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.*;

public class EtcDemo {
    private static volatile LinkedBlockingQueue<Car> etcOne = new LinkedBlockingQueue<>();
    private static volatile LinkedBlockingQueue<Car> ectTwo = new LinkedBlockingQueue<>();
    private static volatile LinkedBlockingQueue<Car> etcThree = new LinkedBlockingQueue<>();
    private static volatile LinkedBlockingQueue<Car> manualOne = new LinkedBlockingQueue<>();
    private static volatile LinkedBlockingQueue<Car> mixOne = new LinkedBlockingQueue<>();


    private static void passCar(LinkedBlockingQueue<Car> carQueue, String queName) {
        while (true) {
            try {
                if (carQueue.size() > 0) {
                    Car car = carQueue.take();
                    Integer waitTime = new Random().nextInt(10);
                    if (car.getChargeType() == 1) {
                        waitTime = waitTime + 10;
                    } else if (car.getChargeType() == 2) {
                        waitTime = waitTime + 15;
                    }
                    System.out.println(String.format("%s --> %s --> %s -->duration %s", car.getCarName(), car.getChargeType(), queName, car.getArriveAtTime() + "-" + waitTime));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private static void passHighWay() {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(5, 5, 10, TimeUnit.MINUTES, new LinkedBlockingQueue<>());
        pool.execute(new Runnable() {
            @Override
            public void run() {
                passCar(etcOne, "etc01");
            }
        });

        pool.execute(new Runnable() {
            @Override
            public void run() {
                passCar(ectTwo, "etc02");
            }
        });

        pool.execute(new Runnable() {
            @Override
            public void run() {
                passCar(etcThree, "etc03");
            }
        });

        pool.execute(new Runnable() {
            @Override
            public void run() {
                passCar(mixOne, "mix01");
            }
        });

        pool.execute(new Runnable() {
            @Override
            public void run() {
                passCar(manualOne, "manual01");
            }
        });

        pool.shutdown();
        try {
            pool.awaitTermination(1, TimeUnit.HOURS);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void enterCar() {
        CopyOnWriteArrayList<Car> cars = new CopyOnWriteArrayList<>();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    Car c = new Car();
                    c.setCarName("ManualCar" + i);
                    c.setChargeType(2);
                    cars.add(c);
                }
            }
        });
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 30; i++) {
                    Car c = new Car();
                    c.setCarName("EtcCar" + i);
                    c.setChargeType(1);
                    cars.add(c);
                }
            }
        });
        thread.start();
        thread1.start();
        while (cars.size() != 40) {
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        for (int i = 0; i < 60; i++) {
            Car c = cars.get(i);
            LinkedBlockingQueue queue = getQueue(c.getChargeType());
            c.setArriveAtTime(i);
            c.setTollgateName(queue.toString());
            System.out.println(String.format("%s-->%s-->%s", c.getCarName(), c.getArriveAtTime(), c.getTollgateName()));
            queue.offer(c);
        }
    }


    private static LinkedBlockingQueue getQueue(int carType) {
        ArrayList<LinkedBlockingQueue> etcQueue = new ArrayList<>();
        etcQueue.add(etcOne);
        etcQueue.add(ectTwo);
        etcQueue.add(etcThree);
        etcQueue.add(mixOne);

        ArrayList<LinkedBlockingQueue> manualQueue = new ArrayList<>();
        manualQueue.add(mixOne);
        manualQueue.add(manualOne);

        if (carType == 1) {
            int minQueueIndex = 0;
            int tmpMixSize = etcQueue.get(0).size();
            for (int i = 0; i < etcQueue.size(); i++) {
                if (etcQueue.get(i).size() < tmpMixSize) {
                    minQueueIndex = i;
                    tmpMixSize = etcQueue.get(i).size();
                }
            }
            return etcQueue.get(minQueueIndex);
        } else {
            int minQueueIndex = 0;
            int tmpMixSize = manualQueue.get(0).size();
            for (int i = 0; i < manualQueue.size(); i++) {
                if (manualQueue.get(i).size() < tmpMixSize) {
                    minQueueIndex = i;
                    tmpMixSize = manualQueue.get(i).size();
                }
            }
            return manualQueue.get(minQueueIndex);
        }
    }

    public static void main(String args[]) {

    }


}

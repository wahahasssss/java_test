package com.hdu;

import org.apache.commons.lang3.SerializationUtils;
import sun.misc.Unsafe;

import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.function.Consumer;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2019/3/6
 * @Time 2:14 PM
 */
public class ConcurrentModifyExceptionMain {
    public static void main(String[] args) {
        while (true) {
            HashMap<Student, Object> maps = new HashMap<Student, Object>();
            for (int i = 0; i < 1000; i++) {
                maps.put(new Student(i), i);
            }

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    for (int i = 0; i < 10000; i++) {
                        maps.put(new Student(1000 + i), 1000 + i);
                    }
                    System.out.println("add maps end...");
                }
            }).start();

            CopyOnWriteArraySet keySets = new CopyOnWriteArraySet();
            keySets.addAll(maps.keySet());
            keySets.forEach(new Consumer() {
                @Override
                public void accept(Object o) {
                    System.out.println(o.toString());
                }
            });
        }

    }


    static class Student {
        private Integer age;

        public Student(Integer age) {
            this.age = age;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "age=" + age +
                    '}';
        }
    }
}

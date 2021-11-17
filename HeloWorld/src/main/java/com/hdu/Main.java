package com.hdu;

import com.hdu.base.Student;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @Author: ssf
 * @Date: 2020/1/8 3:15 下午
 */
public class Main {
    public static void methodA() {
        System.out.println("aaa");
        methodB();

    }

    public static void methodB() {
        System.out.println("bbb");
        methodC();
    }

    public static void methodC() {
//        System.out.println("cccc");
//        System.out.println(Reflection.getCallerClass());
    }

    public static void main(String[] args) throws InterruptedException {
        //ReentrantLock
        List<Integer> ids = Arrays.asList(1, 2, 34, 32, 12);
        List<Integer> least = ids.parallelStream().filter(t -> t > 10).collect(Collectors.toList());
        System.out.println(StringUtils.join(least, ","));
        methodA();
        Thread.sleep(10000);
        System.out.println("begin");
        ThreadPoolExecutor pool = new ThreadPoolExecutor(100, 100, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        for (int i = 0; i < 100000000; i++) {
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    Student student = new Student();
                    System.out.println(student);
                }
            });
        }
        System.out.println("begin end");
        Thread.sleep(10000);
        System.out.println("end");
        System.out.println(UUID.randomUUID().toString());

        System.out.println(System.currentTimeMillis());
        Map<String, String> map1 = new HashMap<>();
        map1.put("1", "one");
//        map1.put("2", "two");
//

        Map<String, String> map2 = new HashMap<>();
        map2.putAll(map1);

        map2.remove("1");


        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println(list.get(list.size() - 1));
        int i = 12;
        System.out.println(i *= ++i);
//
//
//        System.out.println("娃哈哈");
//        System.out.println(countWord("wanaxx"));
    }

    public static int hash(String key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    public static String countWord(String input) {
        char[] inputChar = input.toCharArray();

        HashMap<Character, Integer> charCountMap = new HashMap<>();
        for (char c : inputChar) {
            if (!charCountMap.containsKey(c)) {
                charCountMap.put(c, 1);
            } else {
                int count = charCountMap.get(c);
                count++;
                charCountMap.put(c, count);
            }
        }
        List<Character> chars = new ArrayList<>();
        for (Character c : charCountMap.keySet()) {
            chars.add(c);
        }
        chars.sort(Comparator.comparing(Character::charValue));
        StringBuilder sb = new StringBuilder();
        for (Character c : chars) {
            sb.append(c);
            sb.append(charCountMap.get(c));
        }
        return sb.toString();
    }

}

package com.hdu.lamdademo;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * @author shushoufu
 * @date 2020/07/21
 **/
public class LamdaDemo {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("1,1", "3,2", "4,3", "43,32", "5432,323");
        list.get(0).length();
        List<String> results = list.parallelStream().flatMap(t -> Arrays.stream(t.split(","))).collect(Collectors.toList());
        results.forEach(t -> {
            System.out.println(t);
        });
        List<String> results1 = list.parallelStream().map(t -> t.replace(" ", "")).collect(Collectors.toList());
        results1.forEach(t -> System.out.println(t));
    }
}

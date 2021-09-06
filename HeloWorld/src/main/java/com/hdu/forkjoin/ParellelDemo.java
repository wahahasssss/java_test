package com.hdu.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;

/**
 * @author shushoufu
 * @date 2020/07/27
 **/
public class ParellelDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        IntStream range = IntStream.range(1, 100000);
        new ForkJoinPool(10).submit(()->range.parallel().forEach(t->System.out.println(t))).get();
    }
}

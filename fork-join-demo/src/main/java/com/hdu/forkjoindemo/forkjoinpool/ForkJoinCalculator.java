package com.hdu.forkjoindemo.forkjoinpool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/8/28
 * @Time 下午5:30
 */
public class ForkJoinCalculator implements Calculator{
    private ForkJoinPool pool;
    private static class SumTask extends RecursiveTask<Long> {
        private long[] numbers;
        private int from;
        private int to;

        public SumTask(long[] numbers, int from, int to) {
            this.numbers = numbers;
            this.from = from;
            this.to = to;
        }

        @Override
        protected Long compute() {
            // 当需要计算的数字小于6时，直接计算结果
            if (to - from < 6) {
                long total = 0;
                for (int i = from; i <= to; i++) {
                    total += numbers[i];
                }
                return total;
                // 否则，把任务一分为二，递归计算
            } else {
                int middle = (from + to) / 2;
                SumTask taskLeft = new SumTask(numbers, from, middle);
                SumTask taskRight = new SumTask(numbers, middle+1, to);
                taskLeft.fork();
                taskRight.fork();
                return taskLeft.join() + taskRight.join();
            }
        }
    }

    public ForkJoinCalculator(){
        pool = new ForkJoinPool();
    }

    @Override
    public long sumUp(long[] numbers) {
        return pool.invoke(new SumTask(numbers,0,numbers.length - 1));
    }


    public static void main(String[] args){
        ForkJoinCalculator calculator = new ForkJoinCalculator();
        long result = calculator.sumUp(new long[]{1,2,3,4,5,6,7,8,9});
        System.out.println(result);
    }
}

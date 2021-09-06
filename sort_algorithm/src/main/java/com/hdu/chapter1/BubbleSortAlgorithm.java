package com.hdu.chapter1;

import java.util.Arrays;

/**
 * 冒泡排序
 *
 * 时间复杂度 O（n*n）
 * 空间复杂度O（1）
 *
 * 算法简述：循环遍历数组，比较数据前后两个元素大小，如果大就交换位置，第一次遍历之后，数组最后一个为最大/最小元素。
 */

public class BubbleSortAlgorithm {
    public static int[] sort(int[] sourceArray) throws Exception{
        int[] arr = Arrays.copyOf(sourceArray,sourceArray.length);
        for (int i = 1; i < sourceArray.length;i ++){
            for (int j = 0;j < sourceArray.length - i; j ++){
                if (arr[j] < arr[j+1]){
                    int tmp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = tmp;
                }
            }
        }
        return arr;
    };

    public static void main(String[] args) throws Exception {
        int[] array = new int[] {1,23,4,23,54,29,958,32};
        int[] sortedArray = BubbleSortAlgorithm.sort(array);
        for (int num:
             sortedArray) {
            System.out.println(num);
        }
    }
}

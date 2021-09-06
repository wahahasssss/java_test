package com.hdu.chapter1;

import java.util.Arrays;

/**
 * 选择排序
 * 时间复杂度 是O（n * n）
 * 空间复杂度 是O（1）
 *
 * 循环遍历，找到最大最小值，位置，遍历完后和本次遍历起始位置替换
 */
public class ChooseSortAlgorithm {

    public static int[] sort(int[] sourceArray) throws Exception{
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);
        for (int i = 0;i < sourceArray.length;i++){
            int minIndex = i;
            for (int j = i; j < sourceArray.length;j++){
                if (arr[j] < arr[minIndex]){
                    minIndex = j;
                }
            }
            int tmp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = tmp;
        }
        return arr;
    }

    public static void main(String[] args) throws Exception {
        int[] arr = new int[]{3,43,32,45,98,47,85,34};
        int[] sortedArray = sort(arr);
        for (int num:sortedArray){
            System.out.println(num);
        }
    }
}

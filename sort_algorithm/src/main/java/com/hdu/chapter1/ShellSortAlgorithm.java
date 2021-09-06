package com.hdu.chapter1;

import java.util.Arrays;


/**
 * 希尔排序
 * 插入排序的进阶
 */
public class ShellSortAlgorithm {

    public static int[] sort(int[] sourceArray){
        // 对 arr 进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

        int gap = 1;
        while (gap < arr.length) {
            gap = gap * 3 + 1;
        }

        while (gap > 0) {
            for (int i = gap; i < arr.length; i++) {
                int tmp = arr[i];
                int j = i - gap;
                while (j >= 0 && arr[j] > tmp) {
                    arr[j + gap] = arr[j];
                    j -= gap;
                }
                arr[j + gap] = tmp;
            }
            gap = (int) Math.floor(gap / 3);
        }

        return arr;
    }


    public static void main(String[] args){
        int[] arr = new int[]{1,23,43,46,87,64};
        int[] sortedArr = sort(arr);
        for (int i = 0; i < sortedArr.length;i++){
            System.out.println(sortedArr[i]);
        }
    }
}

package com.hdu.chapter1;

import java.util.Arrays;

/**
 * 插入排序，简单来就是循环遍历，遍历是在排好序的队列中找到插入位置
 * O（n*n）
 */
public class InsertSortAlgorithm {

    public static int[] sort(int[] sourceArray)throws Exception{
        int[] arr = Arrays.copyOf(sourceArray,sourceArray.length);

        // 从下标为1的元素开始选择合适的位置插入，因为下标为0的只有一个元素，默认是有序的
        for (int i = 1; i < arr.length; i++) {

            // 记录要插入的数据
            int tmp = arr[i];

            // 从已经排序的序列最右边的开始比较，找到比其小的数
            int j = i;
            while (j > 0 && tmp < arr[j - 1]) {
                arr[j] = arr[j - 1];
                j--;
            }

            // 存在比其小的数，插入
            if (j != i) {
                arr[j] = tmp;
            }

        }
        return arr;
    }


    public static void main(String[] args) throws Exception {
        int[] arr = new int[]{37,47,43,43,94,43,877,45};
        int[] sortedArr = sort(arr);
        for (int num:sortedArr){
            System.out.println(num);
        }
    }
}

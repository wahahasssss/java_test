package com.hdu.chapter1;

import java.util.Arrays;

/**
 * 堆排序
 *
 * 利用数据结构堆 排序
 *
 * 大顶堆，每个节点的值都大于或等于其子节点的值，在对拍讯中用于升序排列
 * 小顶堆，每个节点的值都小于或者等于其子节点的值，在堆排序算法中用于降序排列
 *
 * 堆排序的平均时间复杂度为 O（nlogn）
 */
public class HeapSortAlgorithm {

    public int[] sort(int[] sourceArray) throws Exception {
        // 对 arr 进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

        int len = arr.length;

        buildMaxHeap(arr, len);

        for (int i = len - 1; i > 0; i--) {
            swap(arr, 0, i);
            len--;
            heapify(arr, 0, len);
        }
        return arr;
    }

    private void buildMaxHeap(int[] arr, int len) {
        for (int i = (int) Math.floor(len / 2); i >= 0; i--) {
            heapify(arr, i, len);
        }
    }

    private void heapify(int[] arr, int i, int len) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int largest = i;

        if (left < len && arr[left] > arr[largest]) {
            largest = left;
        }

        if (right < len && arr[right] > arr[largest]) {
            largest = right;
        }

        if (largest != i) {
            swap(arr, i, largest);
            heapify(arr, largest, len);
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) throws Exception {
        HeapSortAlgorithm heapSortAlgorithm = new HeapSortAlgorithm();
        int[] arr = new  int[]{1,43,76,48,93,65,98};
        for (int i:heapSortAlgorithm.sort(arr)){
            System.out.println(i);
        }
    }

}

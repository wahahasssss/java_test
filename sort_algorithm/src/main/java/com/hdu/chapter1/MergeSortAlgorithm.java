package com.hdu.chapter1;


import java.util.Arrays;

/**
 * 归并排序
 * 分治法的一种典型应用
 *
 * 递归算法
 *
 */
public class MergeSortAlgorithm {

    public static int[] sort(int[] sourceArray) throws Exception{
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);
        int middle = Math.floorDiv(sourceArray.length,2);
        if (arr.length<2){
            return arr;
        }
        int[] left = Arrays.copyOfRange(sourceArray,0,middle);
        int[] right = Arrays.copyOfRange(sourceArray,middle,sourceArray.length);
        return merge(sort(left),sort(right));
    }

    private static int[] merge(int[] leftArray,int[] rightArray){
        int[] result = new int[leftArray.length + rightArray.length];
        int i = 0;
        while (leftArray.length > 0 && rightArray.length > 0){
            if (leftArray[0] <= rightArray[0]){
                result[i++] = leftArray[0];
                leftArray = Arrays.copyOfRange(leftArray,1,leftArray.length);
            }else {
                result[i++] = rightArray[0];
                rightArray = Arrays.copyOfRange(rightArray,1,rightArray.length);
            }
        }

        while (leftArray.length >0){
            result[i++] = leftArray[0];
            leftArray = Arrays.copyOfRange(leftArray,1,leftArray.length);
        }

        while (rightArray.length > 0){
            result[i++] = rightArray[0];
            rightArray = Arrays.copyOfRange(rightArray,1,rightArray.length);
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        int[] arr = new int[]{1,32,66,45,76,35,87,465};
        int[] sortedArr = sort(arr);
        for (int i = 0;i < arr.length;i++){
            System.out.println(sortedArr[i]);
        }
    }
}

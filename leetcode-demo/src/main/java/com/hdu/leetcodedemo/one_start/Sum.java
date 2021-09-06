package com.hdu.leetcodedemo.one_start;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/8/27
 * @Time 下午7:30
 */
public class Sum {
    public int[] twoSum(int[] nums, int target) {
        List<Integer> inputs = new ArrayList<>();
        for(int num:nums){
            inputs.add(num);
        }

        for (int smallNumber : inputs){
            int sub_number = target - smallNumber;
            if (inputs.contains(sub_number)){
                if (sub_number == smallNumber&&inputs.indexOf(sub_number)!=inputs.lastIndexOf(sub_number)){
                    return new int[]{
                            inputs.indexOf(smallNumber),inputs.lastIndexOf(sub_number)};
                }
                if (sub_number == smallNumber){
                    continue;
                }
                return new int[]{
                        inputs.indexOf(smallNumber),inputs.indexOf(sub_number)};

            }
        }
        return new int[]{};
    }
    public int[] twoSumSevenTwo(int[] nums, int target) {
        int[] result=new int[2];
        int[] numsCopy=nums.clone();
        Arrays.sort(numsCopy);
        int smallNumIndex=0;
        int bigNumIndex=nums.length-1;
        while(smallNumIndex < bigNumIndex){
            int sumTemp=numsCopy[smallNumIndex]+numsCopy[bigNumIndex];
            if(sumTemp>target){
                --bigNumIndex;
            }else if(sumTemp==target){
                break;
            }else{
                ++smallNumIndex;
            }
        }
        int t=0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]==numsCopy[smallNumIndex]||nums[i]==numsCopy[bigNumIndex]){
                result[t]=i;
                ++t;
            }
            if(t>1)
                break;
        }
        return result;

    }

    public int[] twoSumSeven(int[] numbers, int target) {
        int [] res = new int[2];
        if(numbers==null||numbers.length<2)
            return res;
        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
        for(int i = 0; i < numbers.length; i++){
            if(!map.containsKey(target-numbers[i])){
                map.put(numbers[i],i);
            }else{
                res[0]= map.get(target-numbers[i]);
                res[1]= i;
                break;
            }
        }
        return res;
    }

    /**
     * 416. 分割等和子集
     * 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
     * @param nums
     * @return
     */
    public static boolean canPartition(int[] nums) {
        for (int i = 0; i < nums.length; i++){

            for (int m = i ; m < nums.length; m ++){
                if (nums[m] < nums[i]){
                    int tmp = nums[i];
                    nums[i] = nums[m];
                    nums[m] = tmp;
                }
            }
        }

        for (int i = 0;i < nums.length; i++){
            int sum1 = 0;
            int sum2 = 0;
            for (int n = 0; n < nums.length;n++){
                if ( n <= i){
                    sum1 += nums[n];
                }else {
                    sum2 += nums[n];
                }
            }
            if (sum1 == sum2){
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args){
        Sum sum = new Sum();
        int[] results = sum.twoSumSeven(new int[]{0,4,3,0},0);
        System.out.println(canPartition(new int[]{1,5,11,5}));
        System.out.println("sum over");
    }
}

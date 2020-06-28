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
    public static void main(String[] args){
        Sum sum = new Sum();
        int[] results = sum.twoSumSeven(new int[]{0,4,3,0},0);
        System.out.println("sum over");
    }
}

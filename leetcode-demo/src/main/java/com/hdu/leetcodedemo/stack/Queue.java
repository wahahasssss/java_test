package com.hdu.leetcodedemo.stack;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName：Queue
 * @Description：TODO
 * @Author：ssf
 * @Date：2021/4/26 2:41 下午
 * @Versiion：1.0
 */
public class Queue {

    List<Integer> queryList = new ArrayList<>();

    private Integer maxValue = -1;



    public Queue(){
    }

    public int max_value() {
        if (queryList.size() == 0){
            return  -1;
        }
        return maxValue;
    }

    public void push_back(int value) {
        queryList.add(value);
        maxValue = Math.max(maxValue, value);
        System.out.println(queryList);
    }


    public int pop_front() {
        if (queryList.size() == 0){
            return -1;
        }
        int value = queryList.remove(0);
        if (value == maxValue){
            calMaxValue();
        }
        System.out.println(queryList);
        return value;
    }

    private void calMaxValue(){
        for (int v:queryList){
            maxValue = Math.max(maxValue, v);
        }
    }
}
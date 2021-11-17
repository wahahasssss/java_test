package com.hdu.leetcodedemo.one_start;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * DESCRIPTION:频率栈
 *
 * @author shushoufu
 * @Date 2018/8/27
 * @Time 下午5:28
 */
class FreqStack {
    private static AtomicInteger MAX_PUSH_COUNT;
    private static AtomicInteger MAX_POP_COUNT;
    private HashMap<Integer, Integer> FREQ_RECORD = new HashMap<>();
    private List<Integer> QUEUE_VALUES = new ArrayList<>();

    public FreqStack() {
        MAX_POP_COUNT = new AtomicInteger(10000);
        MAX_PUSH_COUNT = new AtomicInteger(10000);
    }

    public void push(int x) {
        QUEUE_VALUES.add(x);
        if (!FREQ_RECORD.containsKey(x)) {
            FREQ_RECORD.put(x, 0);
        }
        int fre = FREQ_RECORD.get(x);
        fre = fre + 1;
        FREQ_RECORD.put(x, fre);
    }

    public int pop() {
        int maxValue = Integer.MIN_VALUE;
        List<Integer> max_keys = new ArrayList<>();
        Iterator iterator = FREQ_RECORD.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            Integer key = (Integer) entry.getKey();
            Integer value = (Integer) entry.getValue();
            if (value > maxValue) {
                max_keys.clear();
                maxValue = value;
                max_keys.add(key);
            } else if (maxValue == value) {
                max_keys.add(key);
            }
        }
        Integer max_index = -1;
        for (int key : max_keys) {
            int tmp_index = QUEUE_VALUES.lastIndexOf(key);
            if (tmp_index > max_index) {
                max_index = tmp_index;
            }
        }
        int result = QUEUE_VALUES.remove(max_index.intValue());
        int tmp_freq = FREQ_RECORD.get(result);
        tmp_freq--;
        FREQ_RECORD.put(result, tmp_freq);
        System.out.println(String.format("pop result is : %d", result));
        return result;
    }

    public static void main(String[] args) {
        FreqStack freqStack = new FreqStack();
        freqStack.push(5);
        freqStack.push(7);
        freqStack.push(5);
        freqStack.push(7);
        freqStack.push(4);
        freqStack.push(5);
        freqStack.pop();
        freqStack.pop();
        freqStack.pop();
        freqStack.pop();
    }
}


/**
 * Your FreqStack object will be instantiated and called as such:
 * FreqStack obj = new FreqStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 */
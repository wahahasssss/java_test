package com.hdu.leetcodedemo.stack;

/**
 * @ClassName：StackUtil
 * @Description：TODO
 * @Author：ssf
 * @Date：2021/4/26 2:40 下午
 * @Versiion：1.0
 */
public class StackUtil {


    public static void main(String[] args) {
        Queue queue = new Queue();
        queue.push_back(46);
        queue.max_value();
        queue.pop_front();
        queue.max_value();
        queue.pop_front();
        queue.push_back(868);
        queue.max_value();
        queue.pop_front();
        queue.push_back(525);


    }
}
package com.hdu;

import com.hdu.base.BTree;
import com.hdu.base.Student;
import org.apache.commons.lang3.StringUtils;
import sun.reflect.Reflection;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Author: ssf
 * @Date: 2020/1/8 3:15 下午
 */
public class Main {
    public static void methodA(){
        System.out.println("aaa");
        methodB();

    }

    public static void methodB(){
        System.out.println("bbb");
        methodC();
    }

    public static void methodC(){
        System.out.println("cccc");
        System.out.println(Reflection.getCallerClass());
    }
    public static void main(String[] args){
        //ReentrantLock
        List<Integer> ids = Arrays.asList(1,2,34,32,12);
        List<Integer> least = ids.parallelStream().filter(t->t>10).collect(Collectors.toList());
        System.out.println(StringUtils.join(least,","));
        methodA();
    }

}

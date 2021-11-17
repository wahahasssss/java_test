package com.hdu.offheap;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/12/26
 * @Time 下午5:31
 */
public class HandleOffHeapWithUnsafe {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        field.setAccessible(true);
        Unsafe unsafe = (Unsafe) field.get(null);
        long allocateAddress = unsafe.allocateMemory(10 * 1024 * 1024);
        System.out.println("allocate memory address is  " + allocateAddress);
//        unsafe.putChar("".getBytes());

    }
}

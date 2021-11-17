package com.hdu.test;

public class Demo {
    public static volatile Demo ins = null;
    public static Object obj = new Object();

    public static Demo getInstance() {
        if (ins == null) {
            synchronized (obj) {
                if (ins == null) {
                    ins = new Demo();
                }
            }
        }
        return ins;
    }
}

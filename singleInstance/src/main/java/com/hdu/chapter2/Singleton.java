package com.hdu.chapter2;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/9/27
 * @Time 下午7:21
 */
public class Singleton {
    private static Singleton instance = null;
    private static final Object obj = new Object();

    private Singleton() {
    }


    /**
     * 多线程并不能保证完全单列
     *
     * @return
     */
    public static Singleton newInstance() {
        if (null == instance) {
            instance = new Singleton();
        }
        return instance;
    }


    /**
     * 双重锁机制
     *
     * @return
     */
    public static Singleton newInstanceDoubleCheck() {
        if (null == instance) {
            synchronized (obj) {
                if (null == instance) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}

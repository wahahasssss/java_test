package com.hdu.trycatch;

/**
 * @author shushoufu
 * @date 2020/07/23
 **/
public class TryCatchExecuteOrderDemo {

    public static void normalNoExceptionNoReturn() {
        int i = 1;
        try {
            i++;
            System.out.println("try:" + i);
        } catch (Exception e) {
            i++;
            System.out.println("catch:" + i);
        } finally {
            i++;
            System.out.println("finally:" + i);
        }
    }

    public static void normalExceptionNoReturn() {
        int i = 1;
        try {
            i++;
            int x = i / 0;
            System.out.println("try:" + i);
        } catch (Exception e) {
            i++;
            System.out.println("catch:" + i);
        } finally {
            i++;
            System.out.println("finally:" + i);
        }
    }

    public static void main(String[] args) {
        normalNoExceptionNoReturn();
        normalExceptionNoReturn();
    }
}

package com.hdu.base;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/12/21
 * @Time 下午2:56
 */
public class Atomic {

    private static int byteArrayBaseOffset;

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        /**初始化unsafe类**/
        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        Unsafe UNSAFE = (Unsafe) theUnsafe.get(null);
        System.out.println(UNSAFE);
//        /** 修改字段**/
//        byte[] data = new byte[10];
//        System.out.println(Arrays.toString(data));
//        byteArrayBaseOffset = UNSAFE.arrayBaseOffset(byte[].class);
//        System.out.println(byteArrayBaseOffset);
//        UNSAFE.putByte(data,byteArrayBaseOffset,(byte)1);
//        UNSAFE.putByte(data,byteArrayBaseOffset + 5,(byte)5);
//        System.out.println(Arrays.toString(data));

        /** 修改字段**/


        Student student = new Student(1, "ssf", "32");
        System.out.println(student);
        Field field = student.getClass().getDeclaredField("name");
        long tmpOffset = UNSAFE.objectFieldOffset(field);
        Field idField = student.getClass().getDeclaredField("id");
        long idOffset = UNSAFE.objectFieldOffset(idField);
        System.out.println(tmpOffset + "," + idOffset);
        System.out.println(UNSAFE.getIntVolatile(student, idOffset));
        UNSAFE.putObject(student, tmpOffset, "zzd");

        System.out.println(student);


        /** cas操作**/
        UNSAFE.compareAndSwapInt(student, idOffset, 12, 23);
        System.out.println(student);

    }
}

package com.hdu.base.bitmap;

/**
 * @Author: ssf
 * @Date: 2020/1/21 3:15 下午
 */
public class BitMap {
    public byte[] bitArr;
    private static final byte mask = 3;
    private static final int maxNum = (1 << mask) - 1;
    private long count = 0;

    BitMap() {
        bitArr = new byte[1 << (Integer.SIZE - mask)];
    }

    public void setBit(int num) {

        int index = num >> mask; //通过右移算出需要设置bit位的值所在的byte[]数组的下标
        byte value = bitArr[index];
        byte bit = (byte) (num & maxNum);
        if (value >= 0 && bit == maxNum) {
            bitArr[index] = (byte) ~value;
        } else if (value < 0 && bit != maxNum) {
            bitArr[index] = (byte) ~(~value | (1 << bit));
        } else if (value >= 0 && bit != maxNum) {
            bitArr[index] |= (1 << bit);
        }
    }

    public void printBitMap() {
        System.out.println(bitArr);
    }


    public static void main(String[] args) {
        BitMap bitMap = new BitMap();

        bitMap.setBit(1);

        bitMap.setBit(2);

        bitMap.setBit(3);

        bitMap.setBit(7);

        bitMap.setBit(10);
        bitMap.printBitMap();

    }
}

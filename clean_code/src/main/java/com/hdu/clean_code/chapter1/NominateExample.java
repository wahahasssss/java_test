package com.hdu.clean_code.chapter1;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @Author: ssf
 * @Date: 2020/3/26 11:46 上午
 */
public class NominateExample {
    private static List<int[]> theList = new ArrayList<int[]>();
    private static List<int[]> gameBoard = new ArrayList<int[]>();
    private static Integer STATUS_VALUE = 0;
    private static Integer FLAGGED = 4;
    public void main(String[] args){
        int d; // xxxx
        int elapsedTimeInDays;
        int daysSinceCreation;
        int daysSinceModification;
        int fileAgeInDays;
    }


    public List<int[]> getThem(){
        List<int[]> list1 = new ArrayList<int[]>();
        for (int[] x:theList){  //theList 是啥？
            if (x[0] == 4){     //theList 零下标条目的意义是什么？ 值4代表上面意思
                list1.add(x);
            }
        }
        return list1;
    }

    public List<int[]> getFlaggedCells(){
        List<int[]> flaggedCells = new ArrayList<int[]>();
        for (int[] cell:gameBoard){
            if (cell[STATUS_VALUE] == FLAGGED){
                flaggedCells.add(cell);
            }
        }
        return flaggedCells;
    }

    /**
     * 有意义的区分Processors
     * @param a1
     * @param a2
     */
    public static void copyChars(char[] a1,char[] a2){
        for (int i = 0; i < a1.length;i++){
            a2[i] = a1[i];
        }
    }

    static class DtaRcrd102{
        private Date genymdhms;
        private Date modymdhms;
        private final String pszqint = "102";
    }

    static class Customer{
        private Date generationTimestamp;
        private Date modificationTimestamp;
        private final String recordId = "102";
    }



}

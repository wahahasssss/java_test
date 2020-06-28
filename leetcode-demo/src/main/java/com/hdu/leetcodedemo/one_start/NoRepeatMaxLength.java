package com.hdu.leetcodedemo.one_start;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/10/12
 * @Time 上午11:35
 */
public class NoRepeatMaxLength {
    public static int lengthOfLongestSubstring(String s) {
        String[] strings = s.split("");
        HashSet<String> tmpStrings = new HashSet<>();
        int tmpMaxLength = 0;
        int maxLength = 0;
        for(int i = 0;i<strings.length;i++){
            if (!strings[i].contentEquals("") && !tmpStrings.contains(strings[i])){
                tmpStrings.add(strings[i]);
                tmpMaxLength += 1;
                if (tmpMaxLength>maxLength){
                    maxLength = tmpMaxLength;
                }
            }else {

                tmpMaxLength = 1;
                tmpStrings.clear();
                tmpStrings.add(strings[i]);
            }
        }
        return maxLength;
    }
ClassLoader
    public static void main(String[] args){
        System.out.println(lengthOfLongestSubstring("aab"));

    }
}

package com.hdu;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.lucene.search.BoostQuery;
import org.apache.lucene.search.similarities.Similarity;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/4/27
 * @Time 下午3:02
 */
public class Main {
    public static void main(String[] args){
        try {
            int t = 1/0;
        }catch (Exception e){
            System.out.println(String.format("exception :%s", ExceptionUtils.getStackTrace(e)));
        }
    }
}

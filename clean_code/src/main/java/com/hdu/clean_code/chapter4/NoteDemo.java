package com.hdu.clean_code.chapter4;

import static org.junit.Assert.*;

import org.junit.Test;
import sun.misc.Version;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * @Author: ssf
 * @Date: 2020/3/30 5:44 下午
 */
public class NoteDemo {
    public static void main(String[] args) {
        int a = 1;
        int b = 2;
        assertTrue(a == b); //a == b
        assertTrue(a < b);  //a < b
        assertTrue(a > b);  //a > b
    }

    public static SimpleDateFormat makeStandardHttpDateFormat() {
        // SimpleDateFormat is not thread safe
        // so we need to create each instance independently.
        SimpleDateFormat df = new SimpleDateFormat("EEE, DD MMM yyyy HH:mm:ss z");
        df.setTimeZone(TimeZone.getTimeZone("GMT"));
        return df;
    }

    // TODO these are not needed .We expect this to go away when we do the checkout model.
    protected Version makeVersion() {
        return null;
    }

    /**
     * @param title             for cd
     * @param author            for cd
     * @param tracks            for cd
     * @param durationInMinutes
     */
    public void addCD(String title, String author, int tracks, int durationInMinutes) {

    }

}

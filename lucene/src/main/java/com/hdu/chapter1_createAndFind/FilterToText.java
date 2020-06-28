package com.hdu.chapter1_createAndFind;

import java.io.File;
import java.io.FileFilter;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/4/27
 * @Time 下午3:13
 */
public class FilterToText implements FileFilter{
    /**
     * Tests whether or not the specified abstract pathname should be
     * included in a pathname list.
     *
     * @param pathname The abstract pathname to be tested
     * @return <code>true</code> if and only if <code>pathname</code>
     * should be included
     */
    public boolean accept(File pathname) {
        return pathname.getName().toLowerCase().endsWith(".txt");
    }
}

package com.hdu;

import com.hdu.Annation.UserName;

import java.lang.annotation.Annotation;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/9/25
 * @Time 下午5:12
 */
public class TestClass {
    private String name;

    @UserName(name = "ssf",passWord = "123")
    public void test(){

    }
}

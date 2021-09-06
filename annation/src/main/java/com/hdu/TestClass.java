package com.hdu;

import com.hdu.Annation.UserName;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/9/25
 * @Time 下午5:12
 */
public class TestClass {
    private String name;

    @UserName(name = "#name",passWord = "123")
    public void test(String name){
        System.out.println("name is " + name);
    }
}

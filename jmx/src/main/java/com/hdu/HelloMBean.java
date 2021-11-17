package com.hdu;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/12/27
 * @Time 下午4:06
 */
public interface HelloMBean {
    String getName();

    void setName(String name);

    String getAge();

    void setAge(String age);

    void helloWorld();

    void helloWorld(String str);

    void getTelephone();
}

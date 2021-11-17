package com.hdu;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/12/27
 * @Time 下午4:08
 */
public class Hello implements HelloMBean {
    private String name;
    private String age;

    public String getName() {
        System.out.println("get name " + name);
        return this.name;
    }

    public void setName(String name) {
        System.out.println("set name " + name);
        this.name = name;
    }

    public String getAge() {
        System.out.println("get age " + age);
        return this.age;
    }

    public void setAge(String age) {
        System.out.println("set age " + age);
        this.age = age;
    }

    public void helloWorld() {
        System.out.println("hello world ,name is " + name + " age is " + age);
    }

    public void helloWorld(String str) {
        System.out.println("hello world " + str + ",name is " + name + " age is " + age);
    }

    public void getTelephone() {
        System.out.println("get telephone is " + "xxxxxxx");
    }
}

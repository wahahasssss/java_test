package com.hdu.easyrules.model;

/**
 * @author shushoufu
 * @date 2020/11/08
 **/

public class StudentInfo {
    private String name;
    private Integer age;

    public StudentInfo() {
    }

    public StudentInfo(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}

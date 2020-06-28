package com.hdu.model;

import java.io.Serializable;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/9/19
 * @Time 下午5:31
 */
public class Students implements Serializable{
    private String name;
    private Integer age;

    public Students(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Students{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

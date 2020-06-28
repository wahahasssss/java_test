package com.hdu.iot;

import com.hdu.models.Entiry;

import java.io.Serializable;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/9/19
 * @Time 下午2:09
 */
public class Students implements Serializable{
    private String name;
    private Integer age;
    private Entiry entiry;
    public Students(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Entiry getEntiry() {
        return entiry;
    }

    public void setEntiry(Entiry entiry) {
        this.entiry = entiry;
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
                ", entiry=" + entiry +
                '}';
    }
}

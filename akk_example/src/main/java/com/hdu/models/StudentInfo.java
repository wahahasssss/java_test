package com.hdu.models;

import java.io.Serializable;

public class StudentInfo implements Serializable{
    private Integer age;
    private String name;

    private Entiry entiry;
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

    public Entiry getEntiry() {
        return entiry;
    }

    public void setEntiry(Entiry entiry) {
        this.entiry = entiry;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

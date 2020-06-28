package com.hdu.drools.models;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2019/4/3
 * @Time 10:22 AM
 */
public class StudentModel {
    private String name;
    private Integer age;
    private Integer grade;

    public StudentModel(String name, Integer age, Integer grade) {
        this.name = name;
        this.age = age;
        this.grade = grade;
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

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }
}

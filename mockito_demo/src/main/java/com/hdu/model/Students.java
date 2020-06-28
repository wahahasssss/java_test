package com.hdu.model;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/4/15
 * @Time 下午2:57
 */
public class Students {

    private Integer age;
    private String name;

    public Students(Integer age, String name) {
        this.age = age;
        this.name = name;
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

    @Override
    public String toString() {
        return "Students{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}

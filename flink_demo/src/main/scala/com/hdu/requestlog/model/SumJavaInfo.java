package com.hdu.requestlog.model;

/**
 * @ClassName：SumJavaInfo
 * @Description：TODO
 * @Author：ssf
 * @Date：2021/2/24 2:05 下午
 * @Versiion：1.0
 */
public class SumJavaInfo {
    public String name;
    public Integer age;

    public SumJavaInfo(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public SumJavaInfo() {

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
        return "SumJavaInfo{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
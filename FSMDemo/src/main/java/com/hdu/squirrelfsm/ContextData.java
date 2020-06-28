package com.hdu.squirrelfsm;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/4/11
 * @Time 上午11:29
 */
public class ContextData {
    private Integer age;
    private String name;

    public ContextData() {
    }

    public ContextData(Integer age, String name) {
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
        return "ContextData{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}

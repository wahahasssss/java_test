package com.hdu.drools.models;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2019/4/1
 * @Time 2:12 PM
 */
public class ApplicationModel {
    private String name;
    private Integer age;
    private boolean valid;

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

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    @Override
    public String toString() {
        return "ApplicationModel{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", valid=" + valid +
                '}';
    }
}

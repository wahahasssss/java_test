package com.hdu.model;

import java.util.Optional;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/3/21
 * @Time 下午5:28
 */
public class Person {
    private Optional<String> name;
    private Optional<Integer> age;

    public Person(Optional<String> name, Optional<Integer> age) {
        this.name = name;
        this.age = age;
    }

    public Optional<String> getName() {
        return name;
    }

    public void setName(Optional<String> name) {
        this.name = name;
    }

    public Optional<Integer> getAge() {
        return age;
    }

    public void setAge(Optional<Integer> age) {
        this.age = age;
    }


    @Override
    public String toString() {
        return "Person{" +
                "name=" + name +
                ", age=" + age +
                '}';
    }
}

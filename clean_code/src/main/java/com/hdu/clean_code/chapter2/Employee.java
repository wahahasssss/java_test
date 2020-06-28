package com.hdu.clean_code.chapter2;

/**
 * @Author: ssf
 * @Date: 2020/3/26 3:46 下午
 */

public class Employee {
    private Integer age;
    private EmployeeType type;

    public Employee(Integer age, EmployeeType type) {
        this.age = age;
        this.type = type;
    }

    // Default constructor
    public Employee() {
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public EmployeeType getType() {
        return type;
    }

    public void setType(EmployeeType type) {
        this.type = type;
    }
}
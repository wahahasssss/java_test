package com.hdu.base;

import java.lang.String;
import java.lang.System;

public class Student {
    private int id;
    private String name;
    private String category;

    public Student(int id, String name, String category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String GetName()
    {
    	int id = 0;
    	setName("调下堵上");
    	return id+this.name;
    }
    private void setName(String name)
    {
    	this.name = name;
    }
    public Student()
    {
    	System.out.println("hfejfiejpofjae");
    }

    @Override
    public String toString() {
        return "student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}

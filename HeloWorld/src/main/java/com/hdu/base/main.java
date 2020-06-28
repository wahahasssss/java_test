package com.hdu.base;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2019/6/28
 * @Time 11:42 AM
 */
public class main {
    public static void main(String[] args){
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student("323",12));
        studentList.add(new Student("",12));
//        Map<Integer, Object> map = studentList.stream().collect(Collectors.toMap(Student::getAge, po -> po));//数组转成map结构

//        String testStr = "1323,343,4343232,3232";
//        String[] strings = testStr.split(",",2);
//        System.out.println(JSON.toJSONString(strings));
//        UUID uuid = UUID.randomUUID();
//        System.out.println(uuid);
        List<String> strings = new ArrayList<>();
        strings.add("one");
        strings.add("");
        strings.add(null);

        strings.remove("");
        strings.remove(null);
        String results = StringUtils.join(strings,",");
        System.out.println(results);
    }


    static class Student{
        private String desc;
        private Integer age;

        public Student() {
        }

        public Student(String desc, Integer age) {
            this.desc = desc;
            this.age = age;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }
}

package com.hdu.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author shushoufu
 * @date 2020/11/03
 **/

public class StudentsActivityInfo {
    private String name;
    private Integer age;
    private String birthday;
    private String activityTime; //yyyy-MM-dd HH:mm:ss
    private String activity;
    private Long activityTimestamp;

    private static List<String> activities = Arrays.asList("read", "lunch", "write", "walk");

    private static List<String> names = Arrays.asList("shu", "chen", "zhang", "wang");

    private static List<Integer> ages = Arrays.asList(18, 32, 49, 39, 324);

    public static StudentsActivityInfo randomInit() {
        StudentsActivityInfo student = new StudentsActivityInfo();
        student.setActivity(activities.get(new Random().nextInt(activities.size())));
        student.setName(names.get(new Random().nextInt(names.size())));
        student.setAge(ages.get(new Random().nextInt(ages.size())));
        LocalDateTime now = LocalDateTime.now();
        student.setActivityTime(now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        student.setActivityTimestamp(System.currentTimeMillis());
        return student;
    }

    public Long getActivityTimestamp() {
        return activityTimestamp;
    }

    public void setActivityTimestamp(Long activityTimestamp) {
        this.activityTimestamp = activityTimestamp;
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(String activityTime) {
        this.activityTime = activityTime;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }
}

package cpm.hdu.requestlog.model;

/**
 * @ClassName：StudentsAcitivityInfo
 * @Description：TODO
 * @Author：ssf
 * @Date：2021/3/18 11:40 上午
 * @Versiion：1.0
 */
public class StudentsActivityInfo {
    private String name;
    private Integer age;
    private String birthday;
    private String activityTime;
    private String activity;
    private Long activityTimestamp;

    public StudentsActivityInfo() {
    }

    public StudentsActivityInfo(String name, Integer age, String birthday, String activityTime, String activity, Long activityTimestamp) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
        this.activityTime = activityTime;
        this.activity = activity;
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

    public Long getActivityTimestamp() {
        return activityTimestamp;
    }

    public void setActivityTimestamp(Long activityTimestamp) {
        this.activityTimestamp = activityTimestamp;
    }
}

package Domain;

import java.io.Serializable;

/**
 * Created by B41-80 on 2016/12/15.
 */
public class Students implements Serializable{
    private String Name;
    private int Age;
    private Double Grade;

    public Students() {
    }

    public Students(String name, int age, Double grade) {
        Name = name;
        Age = age;
        Grade = grade;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public Double getGrade() {
        return Grade;
    }

    public void setGrade(Double grade) {
        Grade = grade;
    }
}

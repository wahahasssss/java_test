package BLL;

import Domain.Students;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by B41-80 on 2016/12/15.
 */
public class StudentsBll {
    public static List<Students> GetStudents() {
        List<Students> studentses = new ArrayList<Students>();
        Students s = new Students("shusf", 43, 94.2);
        studentses.add(s);
        return studentses;
    }
}

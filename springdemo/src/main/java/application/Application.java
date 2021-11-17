package application;
/**
 * Created by B41-80 on 2016/12/14.
 */

import BLL.StudentsBll;
import Domain.Students;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/application")
public class Application {

    @RequestMapping("/")
    public String Greeting() {
        return "Hello World";
    }

    @RequestMapping("/hello")
    public String Hello() {
        return "HELLO 界面";
    }

    @RequestMapping("/error")
    public String Error() {
        return "eoor";
    }

    @RequestMapping("/students")
    public List<Students> Students() {
        return StudentsBll.GetStudents();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}

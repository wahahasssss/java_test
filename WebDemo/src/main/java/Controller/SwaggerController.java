package Controller;

import Domain.Param;
import Domain.Students;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by B41-80 on 2016/12/16.
 */
@Api
@Controller
@RequestMapping(value = "/stat")
public class SwaggerController {
    @ResponseBody
    @RequestMapping(value = "/helloword", method = RequestMethod.GET)
    @ApiOperation(nickname = "swagger-helloworld", value = "swagger world", notes = "cesh wefwefewf")
    public String HelloWorld(@ApiParam(value = "nickename") @RequestParam String nickname) {
        return "Hello World," + nickname;
    }

    @ResponseBody
    @RequestMapping(value = "/students", method = RequestMethod.POST)
    @ApiOperation(nickname = "swagger-students", value = "swager-studentsss", notes = "object")
    public List<Students> AllStudents(@ApiParam(value = "input") @RequestBody Param p) {
        Students s = new Students();
        s.setAge(22);
        s.setGrade(99.9);
        s.setName("ssf");
        List<Students> studentses = new ArrayList<Students>();
        studentses.add(s);
        return studentses;
    }
}

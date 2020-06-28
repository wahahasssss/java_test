package Controller;

import Domain.Param;
import Domain.Students;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

/**
 * Created by B41-80 on 2016/12/15.
 */
@Controller
@RequestMapping(value ="/students")
public class StudentController {
    @ResponseBody
    @RequestMapping(value = "/helloworld",method = RequestMethod.GET)
    @ApiOperation(nickname = "swagger-helloworld",value = "MySwagger",notes = "Just For Swagger Test")
    public String HelloWorld(@ApiParam(value="ssf") @RequestParam String name)
    {
        return "HelloWorld"+name;
    }
    @ResponseBody
    @RequestMapping(value = "/stu",method = RequestMethod.POST)
    @ApiOperation(nickname = "swagger-students",value = "Swagger Students",notes = "ceshi")
    public Students students(@ApiParam(value = "shfiwefe") @RequestBody Param p)
    {
        Students s = new Students("sffefwefw",53,99.9);
        return  s;
    }
}

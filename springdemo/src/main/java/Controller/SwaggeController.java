package Controller;

import Domain.ParamStudent;
import Domain.Students;
import application.Application;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by B41-80 on 2016/12/15.
 */
@SpringBootApplication
@Controller
@RequestMapping(value = "/myswagger")
public class SwaggeController {

    @ResponseBody
    @RequestMapping(value = "/helloworld",method = RequestMethod.GET)
    @ApiOperation(nickname = "swagger-helloword",value = "MySwagger World",notes = "Just for test Swagger")
    public String HelloWorld(@ApiParam(value = "shusf") @RequestParam String nickname)
    {
        return  "HelloWorld"+nickname;
    }

    @ResponseBody
    @RequestMapping(value = "/students",method = RequestMethod.POST)
    @ApiOperation(nickname = "swagger-students",value = "Swagger的student",notes = "测试java的swagger")
    public Students students(@ApiParam(value = "输入") @RequestBody ParamStudent s)
    {
        Students stu = new Students("shusf",32,99.9);
        return stu;
    }
    public static void main(String[] args)
    {
        SpringApplication.run(Application.class,args);
    }
}

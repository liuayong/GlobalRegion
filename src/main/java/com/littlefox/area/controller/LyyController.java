package com.littlefox.area.controller;

import com.byd.tool.PrintUtil;
import com.hspedu.pojo.Student;
import com.hspedu.pojo.User;
import com.littlefox.area.vo.Result;
import com.mexue.middle.school.entity.Teacher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/lyy")
public class LyyController {

    @Resource
    private HttpServletRequest request;

    /**
     * /lyy/result1?id=3&name=刘亚勇&name=刘阿勇
     *
     * @return
     */
    @GetMapping("/result1")
    public Result result1() {
        List<Student> stuList = Student.getList();
        log.info("stuList = {}", stuList);
        return Result.success(request.getParameterMap());
    }


    @PostMapping("/body1")
    public Result body1(@RequestBody Teacher teacher) {
        PrintUtil.println(teacher);
        return Result.success(teacher);
    }

}

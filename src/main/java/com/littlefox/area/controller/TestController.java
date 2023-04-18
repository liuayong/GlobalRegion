package com.littlefox.area.controller;

import com.littlefox.area.vo.Result;
import com.mexue.middle.school.entity.Teacher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private HttpServletRequest request;

    /**
     * /test/result1?id=3&name=刘亚勇&name=刘阿勇
     *
     * @return
     */
    @GetMapping("/result1")
    public Result result1() {
        return Result.success(request.getParameterMap());
    }


    @PostMapping("/body1")
    public Result body1(@RequestBody Teacher teacher) {
        return Result.success(teacher);
    }

}

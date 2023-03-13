package com.mexue.middle.school.mapper;

import com.littlefox.area.Application;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class RefIdMapperTest {
    @Resource
    private RefIdMapper refIdMapper;
    @Resource
    private RefIdMapper mapper;

    // @Test
    public void testRefId() {
        String test1 = mapper.test1("luck123-test1");
        String test2 = mapper.test2("luck123-test2");
        String test3 = mapper.test3("luck123-test3");
        String test4 = mapper.test4("luck123-test5");


    }

    @Test
    public void test1() {
        String result = mapper.test1("luck123-test1");
        System.out.println("result = " + result);
        Assert.assertEquals(result, "luck123-test1 luckprop-test1");
    }

    @Test
    public void test2() {
        String result = mapper.test2("luck123-test2");
        System.out.println("result = " + result);
        Assert.assertEquals(result, "luck123-test2 luck123-test2");
    }

    @Test
    public void test3() {
        String result = mapper.test3("luck123-test3");
        System.out.println("result = " + result);
        Assert.assertEquals(result, "luck123-test3 luck123-test3");
    }

    @Test
    public void test4() {
        String result = mapper.test4("luck123-test4");
        System.out.println("result = " + result);
        Assert.assertEquals(result, "luck123-test4 luck123-test4");
    }
}
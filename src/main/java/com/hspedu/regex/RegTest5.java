package com.hspedu.regex;

import com.byd.tool.PrintUtil;
import com.hspedu.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Project: GlobalRegion
 * @Description
 * @Author: Administrator
 * @Create: 2023/3/9
 **/
@Slf4j
public class RegTest5 {
    
    @Test
    /*
    题目1：数字的千分位分割法   将123456789转化为123,456,789

     */
    public void test1() {
        String num = "123456789";
        String[] split = num.split("\\d{3}");
        System.out.println(split.length);
        PrintUtil.println(split);
        
        List<String> subList = new ArrayList<>();
        
    }
    
    
    @Test
    public void test2() {
        String num = "12３２，。34你好89这个，";
        StringUtil.iteration(num);
    }
    
}

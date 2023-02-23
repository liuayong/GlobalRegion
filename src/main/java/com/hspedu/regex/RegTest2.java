package com.hspedu.regex;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class RegTest2 {
    /**
     * java 正则表达式： https://juejin.cn/post/6844903997963255821
     */
    @Test
    public void test() {
    
    }
    
    /**
     * 位置问题: https://blog.csdn.net/liang0000zai/article/details/46442615
     */
    @Test
    public void test1() {
        String ss = "hiooaaoohellowwrld";
        Pattern pt = Pattern.compile("(o+)");
        Matcher mt = pt.matcher(ss);
        System.out.println(mt.lookingAt());
        System.out.println(mt.matches());
        //mt.reset();
        while (mt.find()) {
            log.info(mt.group(1) + "||" + mt.start() + " mt.end()={}", mt.end());
        }
    }
}

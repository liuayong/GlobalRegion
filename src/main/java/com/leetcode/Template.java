package com.leetcode;

import com.littlefox.area.utils.TraceUtil;
import org.junit.Test;
import org.springframework.util.StopWatch;

/**
 * @Project: GlobalRegion
 * @Description
 * @Author: Administrator
 * @Create: 2023/6/8
 **/
public class Template {
    
    @Test
    public void test1() {
        StopWatch stopWatch = new StopWatch(TraceUtil.getClassName() + ":" + TraceUtil.getMethodName());
        stopWatch.start(TraceUtil.getMethodName());
        
        
        String s = "";
        for (int i = 0; i < 100000; i++) {
            s += i;
        }
        stopWatch.stop();
        System.out.println(stopWatch.getLastTaskTimeMillis());
        System.out.println(stopWatch.getTotalTimeMillis());
        System.out.println(stopWatch.prettyPrint());
        
    }
    
    @Test
    public void test2() {
    
    
    }
    
    @Test
    public void test3() {
    
    
    }
    
    @Test
    public void test4() {
    
    
    }
    
    @Test
    public void test5() {
    
    
    }
    
    
}

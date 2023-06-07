package com.leetcode.array;

import com.littlefox.area.utils.TraceUtil;
import org.junit.Test;
import org.springframework.util.StopWatch;

/**
 * @Project: GlobalRegion
 * @Description
 * @Author: Administrator
 * @Create: 2023/6/8
 **/
public class BinSearch {
    
    @Test
    public void test1() {
        StopWatch stopWatch = new StopWatch(TraceUtil.getClassName() + ":" + TraceUtil.getMethodName());
        stopWatch.start(TraceUtil.getMethodName());
        
        int[] nums = {-1, 0, 3, 5, 6, 8, 9, 12};
        int searchVal = -9;
        System.out.println("search1 = " + search1(nums, searchVal));
        System.out.println("search2 = " + search2(nums, searchVal));
        
        stopWatch.stop();
        System.out.println(stopWatch.getLastTaskTimeMillis());
        System.out.println(stopWatch.getTotalTimeMillis());
        System.out.println(stopWatch.prettyPrint());
        
    }
    
    public static int search1(int[] nums, int searchVal) {
        int pos = nums.length / 2;
        
        while (pos >= 0) {
            if (nums[pos] > searchVal) {
                pos = pos / 2;
                if (pos == pos / 2) {
                    pos = -1;
                }
            } else if (nums[pos] < searchVal) {
                pos = (pos + nums.length) / 2;
                if (pos == (pos + nums.length) / 2) {
                    pos = -1;
                }
            } else {
                // 找到位置了
                break;
            }
        }
        return pos;
    }
    
    public static int search2(int[] nums, int searchVal) {
        int left = 0, right = nums.length - 1, middle = 0;
        while (left <= right) {
            middle = (left + right) >> 1;
            if (nums[middle] < searchVal) {
                left = middle + 1;
            } else if (nums[middle] > searchVal) {
                right = middle - 1;
            } else {
                return middle;
            }
        }
        System.out.println("left = " + left);
        System.out.println("right = " + right);
        return -1;
    }
    
    
    @Test
    public void test2() {
        StopWatch stopWatch = new StopWatch(TraceUtil.getClassName() + ":" + TraceUtil.getMethodName());
        stopWatch.start(TraceUtil.getMethodName());
        
        // code here ...
        
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
    }
    
    @Test
    public void test3() {
        StopWatch stopWatch = new StopWatch(TraceUtil.getClassName() + ":" + TraceUtil.getMethodName());
        stopWatch.start(TraceUtil.getMethodName());
        
        // code here ...
        
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
    }
    
    @Test
    public void test4() {
        StopWatch stopWatch = new StopWatch(TraceUtil.getClassName() + ":" + TraceUtil.getMethodName());
        stopWatch.start(TraceUtil.getMethodName());
        
        // code here ...
        
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
    }
    
    @Test
    public void test5() {
        StopWatch stopWatch = new StopWatch(TraceUtil.getClassName() + ":" + TraceUtil.getMethodName());
        stopWatch.start(TraceUtil.getMethodName());
        
        // code here ...
        
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
    }
    
    @Test
    public void test6() {
        StopWatch stopWatch = new StopWatch(TraceUtil.getClassName() + ":" + TraceUtil.getMethodName());
        stopWatch.start(TraceUtil.getMethodName());
        
        // code here ...
        
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
    }
    
    
}

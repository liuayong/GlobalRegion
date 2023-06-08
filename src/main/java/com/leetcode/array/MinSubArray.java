package com.leetcode.array;

import com.byd.tool.PrintUtil;
import com.littlefox.area.utils.TraceUtil;
import org.junit.Test;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Project: GlobalRegion
 * @Description
 * @Author: Administrator
 * @Create: 2023/6/8
 **/
public class MinSubArray {
    
    @Test
    public void test1() {
        StopWatch stopWatch = new StopWatch(TraceUtil.getClassName() + ":" + TraceUtil.getMethodName());
        stopWatch.start(TraceUtil.getMethodName());
        
        
        int[] nums = {2, 3, 1, 2, 4, 3};
        int searchVal = 7;
        
        
        System.out.println("search1 = " + Arrays.toString(searchMinSeqArray(nums, searchVal)));
        
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
        
    }
    
    public static int[] searchMinSeqArray(int[] nums, int compareVal) {
        if (ObjectUtils.isEmpty(nums)) {
            return nums;
        }
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j <= nums.length - 1; j++) {
                sum += nums[j];
                if (sum >= compareVal) {
                    int len = j - i + 1;
                    int[] dst = new int[len];
                    System.arraycopy(nums, i, dst, 0, dst.length);
                    System.out.println("dst = " + Arrays.toString(dst) + "求和: " + Arrays.stream(dst).sum());
                    list.add(dst);
                    break;
                }
            }
        }
        
        // 对List 排序
        //PrintUtil.println(list);
        // 用子元素的长度进行排序
        List<int[]> collect = list.stream().sorted(Comparator.comparing(e -> e.length)).collect(Collectors.toList());
        //PrintUtil.println(collect);
        
        return ObjectUtils.isEmpty(collect) ? new int[0] : collect.get(0);
    }
    
    
}

package com.leetcode.array;

import java.util.Arrays;

/**
 * @Project: GlobalRegion
 * @Description
 * @Author: Administrator
 * @Create: 2023/6/9
 **/
public class ArrryToolTest {
    
    public static int[] arrayCopy(int nums[], int srcPos, int len) {
        int[] dst = new int[len];
        System.arraycopy(nums, srcPos, dst, 0, Math.min(nums.length - 2, dst.length));
        //System.out.println("dst = " + Arrays.toString(dst));
        return dst;
    }
}

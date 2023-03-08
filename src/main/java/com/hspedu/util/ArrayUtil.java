package com.hspedu.util;

import org.apache.poi.ss.formula.functions.T;

import java.util.Arrays;

/*
1、for循环
2、调用clone（）方法
3、Arrays类中的Arrays.copyOf（）方法
4、copyOfRange（）方法
5、System.arraycopy（）方法
 */

/**
 * @Project: GlobalRegion
 * @Description
 * @Author: Administrator
 * @Create: 2023/3/9
 **/
public  class ArrayUtil {
    
    //public static <T> T[] arrayCopy(T[] src) {
    //    T[] dst = new T[];
    //
    //    return dst;
    //}
    
    public static Object[] arrayCopy3(Object[] src) {
        Object[] dst = Arrays.copyOf(src, src.length);
        return dst;
    }
    
    /**
     * 循环赋值
     *
     * @param src
     * @return
     */
    public static int[] arrayCopy1(int[] src) {
        int[] dst = new int[src.length];
        for (int i = 0; i < src.length; i++) {
            dst[i] = src[i];
        }
        return dst;
    }
    
    /**
     * 克隆
     *
     * @param src
     * @return
     */
    public static int[] arrayCopy2(int[] src) {
        int[] dst = src.clone();
        return dst;
    }
    
    /**
     * Arrays类中的Arrays.copyOf（）方法
     *
     * @param src
     * @return
     */
    public static int[] arrayCopy3(int[] src) {
        int[] dst = Arrays.copyOf(src, src.length);
        return dst;
    }
    
    /**
     * @param src
     * @return
     */
    public static int[] arrayCopy4(int[] src) {
        int[] dst = Arrays.copyOfRange(src, 0, src.length);
        return dst;
    }
    
    /**
     * System.arraycopy（）方法
     *
     * @param src
     * @return
     */
    public static int[] arrayCopy5(int[] src) {
        int[] dst = new int[src.length];
        System.arraycopy(src, 0, dst, 0, src.length);
        return dst;
    }
    
    
}

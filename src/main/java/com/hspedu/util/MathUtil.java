package com.hspedu.util;

import java.math.BigDecimal;

/**
 * @Project: GlobalRegion
 * @Description
 * @Author: Administrator
 * @Create: 2023/3/4
 **/
public class MathUtil {


    public static void main(String[] args) {

        System.out.println(Math.ceil(1));
        System.out.println(Math.ceil(1.0));
        System.out.println(Math.ceil(1.1));
        System.out.println(Math.ceil(3 / 2) + ", " + 3 / 2);
        System.out.println(Math.ceil(3 / 3));
        System.out.println(Math.ceil(3 / 4) + ", " + 3 / 4);

        BigDecimal bigDecimal = new BigDecimal(10);
        //bigDecimal.divide()
    }

    /**
     * 向上取整
     *
     * @param a
     * @param b
     * @return
     */
    public static int divCeil(int a, int b) {
        return a % b == 0 ? a / b : a / b + 1;
    }

    public static int divCeil2(int a, int b) {
        return (int) Math.ceil(a / (b + 0.0));
    }

    public static int divCeil3(int a, int b) {
        return (a + b - 1) / b;
    }

    /**
     * 向下取整
     *
     * @param a
     * @param b
     * @return
     */
    public static int divFloor(int a, int b) {
        return a / b;
    }

    public static int divFloor2(int a, int b) {
        return (int) Math.floor(a / (b + 0.0));
    }

}

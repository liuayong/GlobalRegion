package com.byd.tool;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @Project: GlobalRegion
 * @Description
 * @Author: Administrator
 * @Create: 2023/3/18
 **/
public class PrintUtilTest {
    
    
    private static final int PRINT_SIZE = 5;
    private static final int PRINT_SINGLE_SIZE = 5;
    private static final int PRINT_ARRAY_SIZE = 12;
    
    
    public static String toStringV1(int[] a, int showLen) {
        if (a == null)
            return "null";
        int iMax = Math.min(a.length - 1, showLen - 1);
        if (iMax == -1)
            return "[]";
        
        StringBuffer b = new StringBuffer();
        b.append('[');
        for (int i = 0; ; i++) {
            b.append(a[i]);
            if (i == iMax) { // a.length > showLen 表示还有元素不打印显示
                return a.length > showLen ?
                        b.append(", ... , " + a[a.length - 1] + "]").toString() : b.append(']').toString();
            }
            b.append(", ");
        }
    }
    
    public static String toStringV2(int[] a, int showLen) {
        if (a == null)
            return "null";
        int iMax = Math.min(a.length - 1, showLen - 1);
        if (iMax == -1)
            return "[]";
        
        StringBuffer b = new StringBuffer();
        b.append('[');
        for (int i = 0; i <= iMax; i++) {
            b.append(a[i]);
            if (i == iMax) { // 遍历到最后一个元素时，判断是否打印了全部的元素
                //a.length > showLen ? b.append(", ... , " + a[a.length - 1] + "]") : b.append(']');
                if (a.length > showLen) {
                    b.append(", ... , ").append(a[a.length - 1]).append("]");
                } else {
                    b.append(']');
                }
            } else {
                b.append(", ");
            }
        }
        return b.toString();
    }
    
    public static String toStringV3(int[] a, int showLen) {
        if (a == null)
            return "null";
        int iMax = Math.min(a.length - 1, showLen - 1);
        if (iMax == -1)
            return "[]";
        
        StringBuffer b = new StringBuffer();
        b.append('[');
        for (int i = 0; i <= iMax; i++) {
            PrintUtil.iteration(a, showLen, i, b);
        }
        return b.toString();
    }
    
    
    /**
     * @param data
     * @param showLen
     * @param idx
     * @param buffer
     * @see PrintUtil#iteration(int[], int, int, java.lang.StringBuffer)
     */
    private static void iteration(int[] data, int showLen, int idx, StringBuffer buffer) {
        int iMax = Math.min(data.length - 1, showLen - 1);
        buffer.append(data[idx]);
        if (idx == iMax) { // data.length > showLen 表示还有元素不打印显示
            if (data.length > showLen) {
                buffer.append(", ... , ").append(data[data.length - 1]).append("]");    // 中间用 ... 表示
            } else {
                buffer.append(']');
            }
        } else {
            buffer.append(", ");
        }
    }
    
    
    public static boolean isElePrintOneRow(Object[] collection, int len) {
        
        Object first = collection[0];
        
        boolean flag = first instanceof String || first instanceof Number;
        if (flag) {
            System.out.println(toString(collection, len));
            return true;
        }
        return false;
    }
    
    public static String toStringChar1(char[] a, int showLen) {
        if (a == null)
            return "null";
        int iMax = Math.min(a.length - 1, showLen - 1);
        if (iMax == -1)
            return "[]";
        
        StringBuffer b = new StringBuffer();
        b.append('[');
        for (int i = 0; ; i++) {
            b.append(a[i]);
            if (i == iMax) { // a.length > showLen 表示还有元素不打印显示
                return a.length > showLen ? b.append(", ..." + a.length + "]").toString() : b.append(']').toString();
            }
            b.append(", ");
        }
    }
    
    public static String toStringChar2(char[] a, int showLen) {
        if (a == null)
            return "null";
        int iMax = Math.min(a.length - 1, showLen - 1);
        if (iMax == -1)
            return "[]";
        
        StringBuffer b = new StringBuffer();
        b.append('[');
        for (int i = 0; i <= iMax; i++) {
            PrintUtil.iteration(a, i, showLen, b);
        }
        return b.toString();
    }
    
    private static void printSubCollectionChar1(char[] collection, int len) {
        char[] results = Arrays.copyOf(collection, Math.min(collection.length, len));
        System.out.println(toString(results, collection.length));
    }
    
    private static void printSubCollectionChar2(char[] collection, int len) {
        System.out.print(toString(collection, len));
        // todo 下面的打印 变为灰色
        System.out.print(" 类型: " + collection.getClass().getSimpleName() + ", 数组数目: " + collection.length + " \n");
    }
    
    
    public static String toStringDouble(double[] a, int len) {
        if (a == null)
            return "null";
        int iMax = a.length - 1;
        if (iMax == -1)
            return "[]";
        
        StringBuffer b = new StringBuffer();
        b.append('[');
        for (int i = 0; ; i++) {
            b.append(a[i]);
            if (i == iMax) {
                return a.length < len ? b.append(", ... " + a.length + "]").toString() : b.append(']').toString();
            }
            b.append(", ");
        }
    }
    
    
    public static String toString(Object a, int showLen) {
        return a.getClass().getName();
    }
    
    public static void printSubCollection(Collection collection) {
        PrintUtil.  printSubCollection(collection, PRINT_SIZE);
    }
    
    private static void printSubCollection(Map collection) {
        PrintUtil.printSubCollection(collection, PRINT_SINGLE_SIZE);
    }
    
    
    public static void printSubCollection(List collection) {
        PrintUtil.printSubCollection(collection, PRINT_SIZE);
    }
    
    public static void printSubCollection(Object[] collection) {
        
        PrintUtil.printSubCollection(collection, PRINT_SIZE);
        // todo 下面的打印 变为灰色
        System.out.print(" 类型: " + collection.getClass().getSimpleName() + ", 数组数目: " + collection.length + " \n");
    }
    
    public static void printSubCollection(double[] collection) {
        PrintUtil.printSubCollection(collection, PRINT_SIZE);
    }
    
    
    
    
    
    
    @Test
    public void testType() {
        Integer d1 = 3;
        int d2 = 5;
        Object d3 = d2;
        
        int[] arr1 = {1, 2, 3};
        Integer[] arr2 = {1, 2, 3};
        
        System.out.println((d1 instanceof Integer) + ", " + (d1 instanceof Object) + ", " + d1.getClass() + d1.doubleValue());
        System.out.println((d3 instanceof Integer) + ", " + (d3 instanceof Object) + ", " + d3.getClass());
        
        // java: 不兼容的类型: int[]无法转换为java.lang.Integer[]
        System.out.println((arr1 instanceof int[]) + ", " + (arr1 instanceof Object) + ", " + arr1.getClass());
        System.out.println((arr2 instanceof Integer[]) + ", " + (arr2 instanceof Object) + ", " + arr2.getClass());
        System.out.println((arr2 instanceof Object[]));
        //System.out.println((arr1 instanceof Object[]) );
        System.out.println((arr2 instanceof Object[]));
        
    }
    
    @Test
    public void testDouble() {
        Double d1 = 0.3;
        double d2 = 0.5;
        Object d3 = d2;
        
        System.out.println((d1 instanceof Double) + ", " + (d1 instanceof Object) + ", " + d1.getClass() + d1.doubleValue());
        // Inconvertible types; cannot cast 'double' to 'java.lang.Object'
        //System.out.println((d2 instanceof  Double) + ", " + (d2 instanceof  Object));
        System.out.println((d3 instanceof Double) + ", " + (d3 instanceof Object) + ", " + d3.getClass());
        
    }
    
}

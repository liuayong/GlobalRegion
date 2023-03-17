package com.byd.tool;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.LocalTime;
import java.util.*;

@Slf4j
public class PrintUtil {
    
    public static final int PRINT_SINGLE_SIZE = 5;
    public static final int PRINT_ARRAY_SIZE = 12;
    public static final int SHOW_LEN = 20; // primary 类型显示长度
    
    /**
     * 带有默认长度的打印方法, 不同的类型 打印的默认长度不一样
     *
     * @param obj
     */
    public static void println(Object obj) {
        //println(obj, PRINT_SIZE);  switch
        
        if (obj instanceof String) {
            System.out.print("类型: " + obj.getClass().getSimpleName() + " : ");
            System.out.println(obj);
        } else if (obj instanceof Number) {
            System.out.print("类型: " + obj.getClass().getSimpleName() + " : ");
            System.out.println(obj);
        } else if (obj instanceof List) {
            println(obj, PRINT_SINGLE_SIZE);
        } else if (obj instanceof Map) {
            println(obj, PRINT_SINGLE_SIZE);
        } else if (obj instanceof Collection) {
            println(obj, PRINT_SINGLE_SIZE);
        } else if (obj instanceof int[]) {
            println(obj, PRINT_SINGLE_SIZE);
        } else if (obj instanceof double[]) {
            println(obj, PRINT_ARRAY_SIZE);
        } else if (obj instanceof Character[]) {
            println(obj, PRINT_ARRAY_SIZE);
        } else if (obj instanceof char[]) {
            println(obj, PRINT_ARRAY_SIZE);
        } else if (obj instanceof Object[]) {
            println(obj, PRINT_ARRAY_SIZE);
        } else {
            if (obj != null) {
                System.out.print("类型: " + obj.getClass().getSimpleName() + " : ");
            }
            System.out.println(obj);
        }
    }
    
    
    /**
     * 打印方法
     *
     * @param obj
     */
    public static void println(Object obj, int len) {
        if (obj instanceof String) {
            System.out.print("类型: " + obj.getClass().getSimpleName() + " : ");
            System.out.println(obj);
        } else if (obj instanceof Number) {
            System.out.print("类型: " + obj.getClass().getSimpleName() + " : ");
            System.out.println(obj);
        } else if (obj instanceof List) {
            List collection = (List) obj;
            // System.out.println("集合数目: " + collection.size());
            printSubCollection(collection, len);
        } else if (obj instanceof Map) {
            Map collection = (Map) obj;
            System.out.println("Map数目: " + collection.size());
            printSubCollection(collection, len);
        } else if (obj instanceof Collection) {
            Collection collection = (Collection) obj;
            printSubCollection(collection, len);
        } else if (obj instanceof int[]) {
            int[] collection = (int[]) obj;
            printSubCollection(collection, len);
        } else if (obj instanceof double[]) {
            double[] collection = (double[]) obj;
            //System.out.print("类型: " + obj.getClass().getSimpleName() + ", 数组数目: " + collection.length + " \n");
            // Collections.singletonList(collection)
            printSubCollection(collection, len);
        } else if (obj instanceof char[]) {
            char[] collection = (char[]) obj;
            //System.out.print("类型: " + obj.getClass().getSimpleName() + ", 数组数目: " + collection.length + " \n");
            // Collections.singletonList(collection)
            printSubCollection(collection, len);
        } else if (obj instanceof Character[]) {
            Character[] collection = (Character[]) obj;
            //System.out.print("类型: " + obj.getClass().getSimpleName() + ", 数组数目: " + collection.length + " \n");
            // Collections.singletonList(collection)
            printSubCollection(collection, len);
        } else if (obj instanceof Object[]) {
            Object[] collection = (Object[]) obj;
            //System.out.print("类型Before: " + obj.getClass().getSimpleName() + ", 数组数目: " + collection.length + " \n");
            printSubCollection(collection, len);
        } else {
            if (obj != null) {
                System.out.print("类型: " + obj.getClass().getSimpleName() + " : ");
            }
            System.out.println(obj);
        }
    }
    
    
    public static void printSubCollection(Map collection, int len) {
        int count = 0;
        for (Object o : collection.entrySet()) {
            if (count >= len) {
                break;
            }
            System.out.print(count + "): ");
            System.out.println(o);
            count++;
        }
    }
    
    public static void printSubCollection(List collection, int len) {
        if (collection.isEmpty()) {
            return;
        }
        
        Object first = collection.get(0);
        // System.out.println("集合元素类型 = " + first.getClass());     // class java.lang.String
        // System.out.println(first.getClass().equals(String.class));  // true
        // System.out.println(first.getClass() == String.class);       // true
        // System.out.println(first instanceof String);                // true
        
        if (isElePrintOneRow(first)) {
            System.out.println(toString(collection.toArray(), len));
            return;
        }
        
        System.out.println("集合: " + first.getClass().getName() + " 数目: " + collection.size());
        // 打印对象类型
        //System.out.println(collection.subList(0, Math.min(len, collection.size())));
        for (int i = 0; i < Math.min(len, collection.size()); i++) {
            System.out.println(i + "]: " + collection.get(i));
        }
    }
    
    
    public static void printSubCollection(Object[] collection, int len) {
        if (collection == null || collection.length == 0) {
            log.warn("数组{} 没有元素不打印任何内容", collection);
            return;
        }
        if (isElePrintOneRow(collection, len)) {
            return;
        }
        
        for (int i = 0; i < Math.min(len, collection.length); i++) {
            System.out.print(i + "): ");
            System.out.println(collection[i]);
        }
    }
    
    
    public static void printSubCollection(double[] collection, int len) {
        System.out.print(toString(collection, len));
        System.out.print(" 类型: " + collection.getClass().getSimpleName() + ", 数组数目: " + collection.length + " \n");
    }
    
    public static void printSubCollection(int[] collection, int len) {
        //int[] results = Arrays.copyOf(collection, Math.min(collection.length, len));
        System.out.print(toString(collection, len));
        // todo 下面的打印 变为灰色
        System.out.print(" 类型: " + collection.getClass().getSimpleName() + ", 数组数目: " + collection.length + " \n");
    }
    
    public static void printSubCollection(char[] collection, int len) {
        System.out.print(toString(collection, len));
        System.out.print(" 类型: " + collection.getClass().getSimpleName() + ", 数组数目: " + collection.length + " \n");
    }
    
    public static void printSubCollection(Character[] collection, int len) {
        System.out.print(toString(collection, len));
        System.out.print(" 类型: " + collection.getClass().getSimpleName() + ", 数组数目: " + collection.length + " \n");
    }
    
    
    public static void printSubCollection(Collection collection, int len) {
        Iterator iterator = collection.iterator();
        int count = 0;
        
        while (iterator.hasNext()) {
            if (count >= len) {
                break;
            }
            Object o = iterator.next();
            System.out.print(count + "): ");
            System.out.println(o);
            count++;
        }
    }
    
    
    public static String toString(double[] doubles, int showLen) {
        if (doubles == null)
            return "null";
        int iMax = Math.min(doubles.length - 1, showLen - 1);
        if (iMax == -1)
            return "[]";
        
        StringBuffer b = new StringBuffer();
        b.append('[');
        for (int i = 0; i <= iMax; i++) {
            iteration(doubles, i, showLen, b);
        }
        return b.toString();
    }
    
    
    public static String toString(int[] a, int showLen) {
        if (a == null)
            return "null";
        int iMax = Math.min(a.length - 1, showLen - 1);
        if (iMax == -1)
            return "[]";
        
        StringBuffer b = new StringBuffer();
        b.append('[');
        for (int i = 0; i <= iMax; i++) {
            iteration(a, i, showLen, b);
        }
        return b.toString();
    }
    
    
    public static String toString(char[] a, int showLen) {
        if (a == null)
            return "null";
        int iMax = Math.min(a.length - 1, showLen - 1);
        if (iMax == -1)
            return "[]";
        
        StringBuffer b = new StringBuffer();
        b.append('[');
        for (int i = 0; i <= iMax; i++) {
            iteration(a, i, showLen, b);
        }
        return b.toString();
    }
    
    public static String toString(Object[] a, int showLen) {
        if (a == null)
            return "null";
        int iMax = Math.min(a.length - 1, showLen - 1);
        if (iMax == -1)
            return "[]";
        
        StringBuffer b = new StringBuffer();
        b.append('[');
        for (int i = 0; i <= iMax; i++) {
            iteration(a, i, showLen, b);
        }
        return b.toString();
    }
    
    
    /**
     * 迭代处理数组/列表的每个元素
     *
     * @param data
     * @param idx
     * @param showLen
     * @param buffer
     */
    public static void iteration(Object[] data, int idx, int showLen, StringBuffer buffer) {
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
    
    public static void iteration(int[] data, int idx, int showLen, StringBuffer buffer) {
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
    
    public static void iteration(char[] data, int idx, int showLen, StringBuffer buffer) {
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
    
    public static void iteration(double[] data, int idx, int showLen, StringBuffer buffer) {
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
    
    /**
     * 元素是否应该单行打印
     *
     * @param first
     * @return
     */
    public static boolean isElePrintOneRow(Object first) {
        return first instanceof String || first instanceof Number;
    }
    
    /**
     * 是否单行打印
     *
     * @param collection
     * @param len
     * @return
     */
    public static boolean isElePrintOneRow(Object[] collection, int len) {
        if (isElePrintOneRow(collection[0])) {
            System.out.print(toString(collection, len));
            // todo 下面的打印 变为灰色
            System.out.print(" 类型: " + collection.getClass().getSimpleName() + ", 数组数目: " + collection.length + " \n");
            return true;
        }
        return false;
    }
    
    /**
     * 等待打印
     *
     * @param o
     */
    public static void printWait(Object o) {
        System.out.println(LocalTime.now() + " - value: " + o + " - thread: " + Thread.currentThread().getName());
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    
    @Test
    public void testIntArr() {
        int[] ints1 = {1, 2, 33, 1, 8, 2, 65, 25, 888};
        PrintUtil.println(ints1, 2);
        
        Integer[] ints = {1, 2, 33, 1, 8, 2, 65, 25, 8889};
        System.out.println(Arrays.toString(ints));
        PrintUtil.println(ints, 2);
        
        List<Integer> list = Arrays.asList(ints);
        PrintUtil.println(list, 5);
        PrintUtil.println(list, 53);
        PrintUtil.println("Hello 你好 中国！！".toCharArray(), 10);
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        double[] doubles = {1, 2, 33, 1, 8, 2, 65, 25, 888};
        
        PrintUtil.println(doubles, 3);
        //PrintUtil.println(doubles, 10);
    }
}

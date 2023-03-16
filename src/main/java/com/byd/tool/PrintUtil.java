package com.byd.tool;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.LocalTime;
import java.util.*;

@Slf4j
public class PrintUtil {
    
    private static final int PRINT_SIZE = 5;
    private static final int SHOW_LEN = 20; // primary 类型显示长度
    
    /**
     * 打印方法
     *
     * @param obj
     */
    public static void println(Object obj) {
        println(obj, PRINT_SIZE);
    }
    
    public static void printWait(Object o) {
        System.out.println(LocalTime.now() + " - value: " + o + " - thread: " + Thread.currentThread().getName());
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
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
        } else if (obj instanceof Object[]) {
            Object[] collection = (Object[]) obj;
            //System.out.println("数组数目: " + collection.length);
            printSubCollection(collection, len);
        } else if (obj instanceof double[]) {
            double[] collection = (double[]) obj;
            System.out.print("类型: " + obj.getClass().getSimpleName() + ", 数组数目: " + collection.length + " \n");
            // Collections.singletonList(collection)
            printSubCollection(collection, len);
        } else if (obj instanceof int[]) {
            int[] collection = (int[]) obj;
            System.out.print("类型: " + obj.getClass().getSimpleName() + ", 数组数目: " + collection.length + " \n");
            printSubCollection(collection, len);
        } else if (obj instanceof Collection) {
            Collection collection = (Collection) obj;
            printSubCollection(collection, len);
        } else {
            if (obj != null) {
                System.out.print("类型: " + obj.getClass().getSimpleName() + " : ");
            }
            System.out.println(obj);
        }
    }
    
    private static void printSubCollection(Map collection) {
        printSubCollection(collection, PRINT_SIZE);
    }
    
    private static void printSubCollection(Map collection, int len) {
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
    
    private static void printSubCollection(List collection, int len) {
        if (collection.isEmpty()) {
            return;
        }
        
        Object first = collection.get(0);
        // System.out.println("集合元素类型 = " + first.getClass());     // class java.lang.String
        // System.out.println(first.getClass().equals(String.class));  // true
        // System.out.println(first.getClass() == String.class);       // true
        // System.out.println(first instanceof String);                // true
        
        if (first instanceof String || first instanceof Number) {
            System.out.println(toString(collection.toArray(), SHOW_LEN));
            return;
        }
        
        System.out.println("集合: " + first.getClass().getName() + " 数目: " + collection.size());
        // 打印对象类型
        //System.out.println(collection.subList(0, Math.min(len, collection.size())));
        for (int i = 0; i < Math.min(len, collection.size()); i++) {
            System.out.println(i + "]: " + collection.get(i));
        }
        
    }
    
    private static void printSubCollection(List collection) {
        printSubCollection(collection, PRINT_SIZE);
    }
    
    private static void printSubCollection(Object[] collection, int len) {
        if (collection == null || collection.length == 0) {
            log.warn("数组{} 没有元素不打印任何内容", collection);
            return;
        }
        Object first = collection[0];
        if (first instanceof String || first instanceof Number) {
            System.out.println(toString(collection, SHOW_LEN));
            return;
        }
        
        for (int i = 0; i < Math.min(len, collection.length); i++) {
            System.out.print(i + "): ");
            System.out.println(collection[i]);
        }
    }
    
    private static void printSubCollection(Object[] collection) {
        printSubCollection(collection, PRINT_SIZE);
    }
    
    
    private static void printSubCollection(double[] collection, int len) {
        double[] results = Arrays.copyOf(collection, Math.min(collection.length, len));
        System.out.println(toString(results, collection.length)); // Math.max(collection.length, len)
    }
    
    private static void printSubCollection(int[] collection, int len) {
        int[] results = Arrays.copyOf(collection, Math.min(collection.length, len));
        System.out.println(toString(results, collection.length)); // Math.max(collection.length, len)
    }
    
    private static void printSubCollection(double[] collection) {
        printSubCollection(collection, PRINT_SIZE);
    }
    
    private static void printSubCollection(Collection collection, int len) {
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
    
    private static void printSubCollection(Collection collection) {
        printSubCollection(collection, PRINT_SIZE);
    }
    
    public static String toString(double[] a, int len) {
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
    
    public static String toString(int[] a, int showLen) {
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
    
    public static String toString(Object[] a, int showLen) {
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

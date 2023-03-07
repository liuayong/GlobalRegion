package com.byd.tool;

import org.junit.Test;

import java.util.*;

public class PrintUtil {
    
    private static final int PRINT_SIZE = 5;
    
    /**
     * 打印方法
     *
     * @param obj
     */
    public static void println(Object obj) {
        println(obj, PRINT_SIZE);
    }
    
    /**
     * 打印方法
     *
     * @param obj
     */
    public static void println(Object obj, int len) {
        if (obj instanceof List) {
            List collection = (List) obj;
            System.out.println("集合数目: " + collection.size());
            printSubCollection(collection, len);
        } else if (obj instanceof Map) {
            Map collection = (Map) obj;
            System.out.println("Map数目: " + collection.size());
            printSubCollection(collection, len);
        } else if (obj instanceof Object[]) {
            Object[] collection = (Object[]) obj;
            System.out.println("数组数目: " + collection.length);
            printSubCollection(collection, len);
        } else if (obj instanceof double[]) {
            double[] collection = (double[]) obj;
            System.out.print("类型: " + obj.getClass().getSimpleName() + ", 数组数目: " + collection.length + " \n");
            // Collections.singletonList(collection)
            printSubCollection(collection, len);
        } else if (obj instanceof Collection) {
            Collection collection = (Collection) obj;
            System.out.println("集合数目: " + collection.size());
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
        //System.out.println(collection.subList(0, Math.min(len, collection.size())));
        for (int i = 0; i < Math.min(len, collection.size()); i++) {
            System.out.println(i + "]: " + collection.get(i));
        }
    }
    
    private static void printSubCollection(List collection) {
        printSubCollection(collection, PRINT_SIZE);
    }
    
    private static void printSubCollection(Object[] collection, int len) {
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
        
        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = 0; ; i++) {
            b.append(a[i]);
            if (i == iMax) {
                return a.length < len ? b.append(", ...]").toString() : b.append(']').toString();
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

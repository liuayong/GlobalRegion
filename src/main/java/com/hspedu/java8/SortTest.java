package com.hspedu.java8;

import com.byd.tool.PrintUtil;
import com.hspedu.pojo.User;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @Project: GlobalRegion
 * @Description
 * @Author: Administrator
 * @Create: 2023/3/8
 **/
public class SortTest {
    
    @Test
    public void test1() {
        // 定义含有5个元素的数组
        double[] scores = new double[]{78, 45, 85, 97, 87};
        System.out.println("排序前数组内容如下：");
        
        // 对scores数组进行循环遍历
        for (int i = 0; i < scores.length; i++) {
            System.out.print(scores[i] + "\t");
        }
        System.out.println("\n排序后的数组内容如下：");
        
        // 对数组进行排序 升序排列
        Arrays.sort(scores);
        
        // 遍历排序后的数组
        for (int j = 0; j < scores.length; j++) {
            System.out.print(scores[j] + "\t");
        }
    }
    
    @Test
    public void test1_2() {
        Integer[] a = {9, 8, 7, 2, 3, 4, 1, 0, 6, 5};    // 数组类型为Integer
        Arrays.sort(a, Collections.reverseOrder());
        for (int arr : a) {
            System.out.print(arr + " ");
        }
    }
    
    @Test
    public void test1_3() {
        Integer[] a = {9, 8, 7, 2, 3, 4, 1, 0, 6, 5};    // 数组类型为Integer
        Arrays.sort(a, new MyComparator());
        for (int arr : a) {
            System.out.print(arr + " ");
        }
        System.out.println("\n=======================");
        
        Comparator<Integer> comparator = new Comparator<>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                //return o1.compareTo(o2);    // 升序排列
                return o2.compareTo(o1);     // 降序排列
            }
        };
        Arrays.sort(a, comparator);
        for (int arr : a) {
            System.out.print(arr + " ");
        }
    }
    
    
    @Test
    public void testCompare() {
        User user1 = new User(1, "Eric");
        User user2 = new User(2, "John");
        
        //user1.com
        
    }
    
    
    @Test
    public void test2() {
        // 定义含有5个元素的数组
        double[] scores = new double[]{78, 45, 85, 97, 87};
        System.out.println("排序前数组内容如下：");
        
        // 对scores数组进行循环遍历
        PrintUtil.println(scores);
        System.out.println("\n排序后的数组内容如下：");
        
        // 对数组进行排序
        Arrays.sort(scores);
        
        // 遍历排序后的数组
        PrintUtil.println(scores);
    }
    
    // 实现Comparator接口
    class MyComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            /*
             * 如果o1小于o2，我们就返回正值，如果o1大于o2我们就返回负值， 这样颠倒一下，就可以实现降序排序了,反之即可自定义升序排序了
             */
            return o1 - o2;
        }
    }
}

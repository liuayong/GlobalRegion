package com.hspedu.java8.stream;

import com.hspedu.java8.ListTest;
import org.junit.Test;

/**
 * @Project: GlobalRegion
 * @Description
 * @Author: Administrator
 * @Create: 2023/3/17
 **/
public class ThreadDemo {
    
    /**
     * java多线程12-lambda简化线程使用 原创
     * https://blog.51cto.com/u_15522232/5094865
     */
    @Test
    public void test3() {
        new Thread(new ThreadDemo.InnerClass()).start();
        //需要借助接口或者是父类
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 3; i++) {
                    System.out.println("学习2222222");
                }
            }
        }).start();
        
        // 4、匿名内部类，且简化run方法
        // lamdba表达式，对于一个方法可以推导出接口和类
        new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                System.out.println("lamdba表达式");
            }
        }).start();
        
    }
    
    /**
     * 内部类： 对于只使用一次的，可以使用内部类加快运行速率，它的特点是，只有使用时才会进行编译，且内部类的引用更快。
     */
    private static class InnerClass implements Runnable {
        public void run() {
            for (int i = 0; i < 3; i++) {
                System.out.println("学习_(:з」∠)_");
            }
        }
    }
}

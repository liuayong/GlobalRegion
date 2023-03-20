package com.hspedu.test;

import org.junit.Test;

public class BreakAndContiune {


    @Test
    public void test1() {

        for1:
        for (int i = 0; i < 5; i++) {
            System.out.println("第" + (i + 1) + "次循环：");
            for2:
            for (int j = 0; j < 5; j++) {
                if (j == 2) {
                    break for1;//给循环命名，并结束外层循环
                }
                System.out.println(j);
            }
        }

    }

    @Test
    public void test2() {

        for1:
        for (int i = 0; i < 5; i++) {
            System.out.print("第" + (i + 1) + "次循环：");
            for2:
            for (int j = 0; j < 5; j++) {
                if (j == 2 || j == 3) {
                    continue ;//给循环命名，并结束外层循环
                }
                System.out.println(j);
            }
            System.out.println("i = " + i);
        }

    }
}

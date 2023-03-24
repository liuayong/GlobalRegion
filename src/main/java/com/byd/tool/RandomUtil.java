package com.byd.tool;

import java.util.Random;

/**
 * @Project: GlobalRegion
 * @Description
 * @Author: Administrator
 * @Create: 2023/3/24
 **/
public class RandomUtil {
    
    public static int getRandInt() {
        int rand = new Random().nextInt(100);
        return rand;
    }
}

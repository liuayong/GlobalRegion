package com.hspedu.util;

import com.byd.tool.PrintUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * @Project: GlobalRegion
 * @Description
 * @Author: Administrator
 * @Create: 2023/3/9
 **/
public class ArrayUtilTest {
    
    @Test
    public void testArrayCopy() {
        int src[] = {1, 3, 5, 7, 11, 20};
        
        Assert.assertEquals(Arrays.toString(ArrayUtil.arrayCopy1(src)), Arrays.toString(src));
        Assert.assertEquals(Arrays.toString(ArrayUtil.arrayCopy2(src)), Arrays.toString(src));
        Assert.assertEquals(Arrays.toString(ArrayUtil.arrayCopy3(src)), Arrays.toString(src));
        Assert.assertEquals(Arrays.toString(ArrayUtil.arrayCopy4(src)), Arrays.toString(src));
        Assert.assertEquals(Arrays.toString(ArrayUtil.arrayCopy5(src)), Arrays.toString(src));
        PrintUtil.println(ArrayUtil.arrayCopy5(src));
    }
    
}
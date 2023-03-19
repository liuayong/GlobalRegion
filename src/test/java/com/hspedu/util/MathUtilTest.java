package com.hspedu.util;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @Project: GlobalRegion
 * @Description
 * @Author: Administrator
 * @Create: 2023/3/4
 **/
public class MathUtilTest {
    
    @Test
    public void divCeil() {
        assertEquals(MathUtil.divCeil3(3, 2), 2);
        assertEquals(MathUtil.divCeil3(3, 3), 1);
        assertEquals(MathUtil.divCeil3(3, 4), 1);
    }
    
    @Test
    public void divCeil2() {
        assertEquals(MathUtil.divCeil3(3, 2), MathUtil.divCeil2(3, 2));
        assertEquals(MathUtil.divCeil3(3, 3), MathUtil.divCeil2(3, 3));
        assertEquals(MathUtil.divCeil3(3, 4), MathUtil.divCeil2(3, 4));
    }
    
    @Test
    public void divFloor() {
        assertEquals(MathUtil.divFloor(3, 2), 1);
        assertEquals(MathUtil.divFloor(3, 3), 1);
        assertEquals(MathUtil.divFloor(3, 4), 0);
    }
    
    @Test
    public void divFloor2() {
        assertEquals(MathUtil.divFloor(7, 2), MathUtil.divFloor2(7, 2));
        assertEquals(MathUtil.divFloor(7, 3), MathUtil.divFloor2(7, 3));
        assertEquals(MathUtil.divFloor(7, 4), MathUtil.divFloor2(7, 4));
    }
}
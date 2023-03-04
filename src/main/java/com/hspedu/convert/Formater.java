package com.hspedu.convert;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

public class Formater {

    // select RIGHT('0000'+CAST( '123'  AS nvarchar(50)),4) DWBH

    /**
     * 补0操作
     * //字符串格式化长度不足补0
     *
     * @param str
     * @param strLength
     * @return
     */

    public static String addZeroForNum(String str, int strLength) {
        int strLen = str.length();
        if (strLen < strLength) {
            while (strLen < strLength) {
                StringBuffer sb = new StringBuffer();
                sb.append("0").append(str); // 左补0
                // sb.append(str).append("0");//右补0
                str = sb.toString();
                strLen = str.length();
            }
        }
        return str;
    }

    @Test
    public void test2() {
        String str = "123";
        String s = String.format("%06d", str);  //s="000123" IllegalFormatConversionException

        // %06d的定义：
        // 0代表前面要补的字符
        // 6代表字符串长度
        // d表示参数为整数类型
    }

    @Test
    public void test1() {
        String str = "123";
        Assert.assertEquals(StringUtils.leftPad(str, 9, "00"), "000000123");
        Assert.assertEquals(StringUtils.leftPad(str, 9, "0"), "000000123");

        System.out.println(String.format("%9s", str).replaceAll(" ", "0"));
        System.out.println(String.format("%-9s", str));
        System.out.println(String.format("%9s", str));
        System.out.println(addZeroForNum(str, 9));
        System.out.println(String.format("%09d", 123));
        System.out.println(String.format("%09d", Long.parseLong(str)));
    }

}

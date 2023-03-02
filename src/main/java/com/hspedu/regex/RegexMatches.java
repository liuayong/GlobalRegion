package com.hspedu.regex;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexMatches {
    private static String REGEX = "a*b"; //a.*?b
    private static String INPUT = "aabfooaabfooabfoobkkk";
    private static String REPLACE = "-";
    
    public static void main(String[] args) {
        
    }
    
    @Test
    public void demo2() {
        //向前引用：
        Pattern p = Pattern.compile("(\\d(\\d))\\2");
        Matcher matcher = p.matcher("211");
        System.out.println(matcher.matches());
        //结果：true
        //解释："\\2"代表引用前面的第2组匹配的值
        
        p = Pattern.compile("(\\d(\\d))\\1\\2");
        Matcher matcher2 = p.matcher("21211");
        System.out.println(matcher2.matches());
    }
    
    @Test
    public void demo1() {
        Pattern p = Pattern.compile(REGEX);
        // 获取 matcher 对象
        Matcher m = p.matcher(INPUT);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            System.out.print(sb + " ");
            m.appendReplacement(sb, REPLACE);
            System.out.println(sb);
        }
        m.appendTail(sb);
        System.out.println(sb.toString());
    }
}
package com.hspedu.regex;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Slf4j
public class RegTest {
    
    @Test
    public void test1() {
        String text = "这是**加粗1**，这是**加粗2**，其余内容。";
        Pattern boldPattern = Pattern.compile("\\*\\*([^*]+)\\*\\*");//匹配被两个星号"**"围起来的内容
        Matcher m = boldPattern.matcher(text);
        
        
        //log.info("开始: m= {}, m.start()={}, m.end()={}", m, m.start(), m.end());
        //循环搜索所有匹配
        while (m.find()) {
            System.out.println("---------------------");
            System.out.println("[" + m.group(0) + "]");
            System.out.println("[" + m.group(1) + "]");
            System.out.println("[" + m.start() + "]");
            System.out.println("[" + m.end() + "]");
            log.info("循环匹配中: m= {}, m.start()={}, m.end()={}", m, m.start(), m.end());
        }
        
        m.reset();
        System.out.println("\n");
        
        //从指定位置10开始匹配
        if (m.find(8)) {
            System.out.println("===========");
            System.out.println("[" + m.group(0) + "]");
            System.out.println("[" + m.group(1) + "]");
            System.out.println("[" + m.start() + "]");
            System.out.println("[" + m.end() + "]");
        }
    }
    
    /**
     * https://www.cnblogs.com/mengw/p/13531008.html
     */
    @Test
    public void containsSubStr() {
        String str = "eleabapbabahabnt";
        String subStr = "ab";
        int i1 = StringUtils.countOccurrencesOf(str, subStr);
        System.out.println("i1 = " + i1);
        
        int i2 = org.apache.commons.lang3.StringUtils.countMatches(str, subStr);
        System.out.println("i2 = " + i2);
        System.out.println(str.contains(subStr));
        
        boolean matches = str.matches(subStr);
        System.out.println("matches = " + matches);
        
        Pattern p = Pattern.compile(subStr);
        Matcher m = p.matcher(str);
        boolean b = m.find();
        System.out.println("b = " + b);
    }
    
    /***
     * https://www.jianshu.com/p/1ad5517c06f5
     */
    @Test
    public void reg() {
        String reg = "(\\w+),?";
        String str = "aabb,xxx,yysin,ienif";
        Pattern pattern = Pattern.compile(reg);//编译正则表达式(\w+),?
        Matcher matcher = pattern.matcher(str);//用编译后的pattern去匹配目标字符串str
        while (matcher.find()) {//从开头逐个匹配，返回值为”是否发现匹配项“
            System.out.println(matcher.group());//当前匹配结果
            System.out.println(matcher.start());//当前匹配结果首字符在目标字符串中的索引位置index
            System.out.println(matcher.end());//当前匹配结果末位字符在目标字符串中的索引位置index+1
            System.out.println("matcher.groupCount(): " + matcher.groupCount());//正则表达式中捕获项个数
            if (matcher.groupCount() > 0) {
                for (int i = 1; i <= matcher.groupCount(); ++i) {
                    String group = matcher.group(i);//当前匹配结果中第i个捕获结果，i:1, 2, 3…
                    log.info("matcher.group(i) = {}", group);
                }
            }
        }
    }
    
    
    /*
    而Matcher.matches()方法试图将整个区域与模式匹配，如果匹配成功，则可以通过开始、结束和组方法获得更多信息。
即这个方法会在表达式前后加上$（regex$），是对这个字符串全匹配
而不会只匹配其中的子串，如果只想匹配子串
Pattern.compile(regex).matcher(str).find()
     */
    @Test
    public void testMatch() {
        String Str = "www.runoob.com";
        
        System.out.print("返回值 :");
        System.out.println(Str.matches("(.*)runoob(.*)"));
        
        System.out.print("返回值 :");
        System.out.println(Str.matches("(.*)google(.*)"));
        
        System.out.print("返回值 :");
        System.out.println(Str.matches("www(.*)"));
        
        System.out.print("返回值 :");
        System.out.println(Str.matches("www"));
        System.out.print("返回值 :");
        System.out.println(Str.matches("runoob"));
    }
    
    
    /**
     * 全局匹配正则表达式 https://www.cnblogs.com/sanrenblog/p/15989629.html
     *
     * @param str   字符串
     * @param regex 正则表达式
     * @return
     */
    public static boolean regex(String str, String regex) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        while (m.find()) {
            return true;
        }
        return false;
    }
}

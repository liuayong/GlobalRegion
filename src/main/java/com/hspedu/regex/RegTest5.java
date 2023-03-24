package com.hspedu.regex;

import com.byd.tool.PrintUtil;
import com.hspedu.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Project: GlobalRegion
 * @Description
 * @Author: Administrator
 * @Create: 2023/3/9
 **/
@Slf4j
public class RegTest5 {
    
    @Test
    /*
    题目1：数字的千分位分割法   将123456789转化为123,456,789

     */
    public void test1() {
        String num = "123456789";
        String[] split = num.split("\\d{3}");
        System.out.println(split.length);
        PrintUtil.println(split);
        
        List<String> subList = new ArrayList<>();
    }
    
    
    @Test
    public void test2() {
        String num = "12３２，。34你好89这个，";
        StringUtil.iteration(num);
    }
    
    
    @Test
    public void test3() {
        String str = "hello";
        String replace = str.replace("^", "😄");
        System.out.println(replace);
        
        replace = str.replaceFirst("^", "😄");
        System.out.println(replace);
        
        replace = str.replaceAll("$", "😄");
        System.out.println(replace);
    }
    
    @Test
    public void test3_1() {
        String str = "xbx_love_study_1.mp4";
        
        String replace = str.replaceAll("\\b", "😄");
        System.out.println(replace);
        
        replace = str.replaceAll("b", "😄");
        System.out.println(replace);
    }
    
    @Test
    public void test3_2() {
        String str = "[[xxx_love_study_1.mp4]]";
        
        String replace = str.replaceAll("\\B", "😄");
        System.out.println(replace);
        
    }
    
    @Test
    public void test4() {
        String str = "xxx_love_stxxxudy_1.mp4";
        
        String replace = str.replaceAll("(?=xxx)", "😄");
        System.out.println(replace);
        
        replace = str.replaceAll("(?<=xxx)", "😄");
        System.out.println(replace);
        
        
        replace = str.replaceAll("(?!xxx)", "😄");
        System.out.println(replace);
        // 😄x😄x😄x😄_😄l😄o😄v😄e😄_😄s😄t😄x😄x😄x😄u😄d😄y😄_😄1😄.😄m😄p😄4😄
        // 字符串之间 都插入一个字符 str.replaceAll("", "😄")
        // 😄x😄x😄x😄_😄l😄o😄v😄e😄_😄s😄t😄x😄x😄x😄u😄d😄y😄_😄1😄.😄m😄p😄4😄
        
        // x😄x😄x😄_😄l😄o😄v😄e😄_😄s😄tx😄x😄x😄u😄d😄y😄_😄1😄.😄m😄p😄4😄
    }
    
    
    @Test
    public void test4_2() {
        String str = "xxx_love_stxxxudy_1.mp4";
        
        String replace = str.replaceAll("(?<=xxx)", "😄");
        System.out.println(replace);
        
        replace = str.replaceAll("(?<!xxx)", "😄");
        System.out.println(replace);
    }
    
    @Test
    public void test5() {
        String num = "1234567890";
        String replace = num.replaceAll("(\\d{3})", "$1😄");
        System.out.println(replace);
        
        replace = num.replaceAll("(\\d{3}+)$", ",$1");
        System.out.println(replace);
        
        
        String[] split = num.split("3");
        System.out.println(split.length);
        PrintUtil.println(split);
    }
    
    @Test
    public void test5_1() {
        String num = "1234567890";
        String replace = num.replaceAll("(\\d{3})", ",$1");
        System.out.println(replace);
        replace = num.replaceAll("(\\d{3})", "$1,");
        System.out.println(replace);
        
        // 打印千分位的第一种方式  两次字符串反转
        String reverse = (new StringBuffer(num).reverse().toString()).replaceAll("(\\d{3})", "$1,");
        System.out.println(reverse);
        System.out.println(new StringBuffer(reverse).reverse());
        
        replace = num.replaceAll("(?=(\\d{3})+$)", ",");     // + repetition not allowed inside lookbehind
        System.out.println(replace);
        
        // 两者结合就是从后往前每三个数字的位置前添加逗号，但是这个位置不能是^首位。
        replace = num.replaceAll("(?!^)(?=(\\d{3})+$)", ",");
        System.out.println(replace);
        
    }
    
    @Test
    public void test5_2() {
        String mobile = "18379836654";
        String replace = mobile.replaceAll("(?=(\\d{3})+$)", "-");
        System.out.println(replace);
        
        Random random = new Random();
        
        String t = "1";
        for (int i = 0; i < 20; i++) {
            System.out.println(t.replaceAll("(?=(\\d{3})+$)", "-"));
            t = t + random.nextInt(10);
        }
    }
    
    @Test
    public void test5_3() {
        String mobile = "18379836654222";
        String replace = mobile.replaceFirst("(?<=\\d{3})\\d+", "-$0");
        System.out.println(replace);
        
        // 183-7983 6654
        // 183-7983-6654
        replace = replace.replaceFirst("(?<=[\\d-]{8})\\d{1,4}", "-$0");
        System.out.println(replace);
    }
    
    @Test
    public void test5_4() {
        String t = "";
        for (int i = 1; i < 20; i++) {
            System.out.print(formatMobile(t) + "\t\t\t\t\t\t\t");
            System.out.println(formatMoney(t));
            t = t + i;
        }
        String mobile = "18379836654222";
        System.out.println(formatMobile(mobile));
    }
    
    /**
     * 格式化手机号
     *
     * @param str
     * @return
     */
    public String formatMobile(String str) {
        return str.replaceFirst("(?<=\\d{3})\\d+", "-$0").replaceFirst("(?<=[\\d-]{8})\\d+", "-$0");
    }
    
    public String formatMoney(String str) {
        return str.replaceAll("(?<!^)(?=(\\d{3})+$)", ",");
    }
    
    @Test
    public void test6() {
        String regex = "((?=.*\\d)((?=.*[a-z])|(?=.*[A-Z])))";
        Pattern pattern = Pattern.compile(regex);
        //pattern.matcher()
        String s = "hello12world87OK";
        
        String repalce = s.replaceAll(regex, "#");
        System.out.println(repalce);
        repalce = s.replaceAll("(?=.*(\\d))((?=.*([a-z]))|(?=.*([A-Z])))", "$1");
        boolean isMatch = Pattern.compile("(?=.*(\\d))((?=.*([a-z]))|(?=.*([A-Z])))").matcher(s).find();
        System.out.println("是否匹配: " + isMatch + "  " + repalce);
        
        
        String regex1 = "(?<=.{0,50}(\\d))((?=.*([a-z]))|(?=.*([A-Z])))";
        repalce = s.replaceAll(regex1, "$1");
        isMatch = Pattern.compile(regex1).matcher(s).find();
        System.out.println("是否匹配: " + isMatch + "  " + repalce);
        
        
        System.out.println(s.matches(regex));
        
        
        System.out.println("+++++++++++++++++++++++++");
        String[] strs = {"1111", "AAAA", "123AAAA", "321aaaa", "32ahsdl32"};
        for (String str : strs) {
            Matcher matcher = pattern.matcher(str);
            System.out.println(matcher.find() + " " + str.matches(regex));
        }
    }
}

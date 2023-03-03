package com.hspedu.regex;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;

@Slf4j
public class RegTest3 {
    @Test
    public void test1() {
        String regex = "20\\d\\d";
        String input = "2019, hello";
        System.out.println(input.matches(regex)); // true
        System.out.println("2100".matches(regex)); // false
        
        Pattern compile = Pattern.compile(regex);
        Matcher matcher = compile.matcher(input);
        log.info("matcher.find() = {}", matcher.find());
    }
    
    @Test
    public void test2() {
        
        String re1 = "java\\d"; // 对应的正则是java\d
        System.out.println("java9".matches(re1));
        System.out.println("java10".matches(re1));
        System.out.println("javac".matches(re1));
        
        String re2 = "java\\D";
        System.out.println("javax".matches(re2));
        System.out.println("java#".matches(re2));
        System.out.println("java5".matches(re2));
    }
    
    String re = null;
    
    
    /**
     * find与matches的区别
     */
    @Test
    public void testFind() {
        String re = "(\\d{3,4})-(\\d{7,8})";
        String input = "aaa010-12345678111-111456780ok012-999999945678";
        
        Pattern compile = Pattern.compile(re);
        Matcher matcher = compile.matcher(input);
        while (matcher.find()) {
            log.info("groupCount = {}", matcher.groupCount());
            log.info("group 1: " + matcher.group(1));
            log.info("group 2: " + matcher.group(2));
            System.out.println();
        }
    }
    
    @Test
    public void testFind2() {
        String re = "(\\d{3,4})-(\\d{7,8})";
        String input = "012-99996785";
        Pattern compile = Pattern.compile(re);
        Matcher matcher = compile.matcher(input);
        log.info("Matcher.matches = {}", matcher.matches());
        //matcher.reset();
        log.info("Matcher.find = {}", matcher.find());
        log.info("Matcher.find = {}", matcher.find(0));
        
        
    }
    
    @Test
    public void testMatches() {
        String re = "(\\d{3,4})-(\\d{7,8})";
        String input = "012-99996785";
        Pattern compile = Pattern.compile(re);
        Matcher matcher = compile.matcher(input);
        
        log.info("String.matches = {}", input.matches(re));
        log.info("Pattern.matches = {}", Pattern.matches(re, input));
        //log.info("Matcher.matches = {}", matcher.matches());
        log.info("Matcher.find = {}", matcher.find());
        
        if (matcher.matches()) {
            log.info("匹配整个字符串: {}", matcher.groupCount());
            log.info("匹配子串数目: {}", matcher.groupCount());
            log.info("匹配子串1 区号: {}", matcher.group(1));
            log.info("匹配子串2 电话号: {}", matcher.group(2));
        }
        
    }
    
    /**
     * 编写测试用例
     */
    @Test
    public void test3() {
        String re = "(\\d{3,4})-(\\d{7,8})";
        String input = "aaa010-12345678110-111456780ok010-12345678";
        
        Pattern compile = Pattern.compile(re);
        Matcher matcher = compile.matcher(input);
        System.out.println(matcher.matches());
        System.out.println(matcher.find());
        System.out.println(matcher.find());
        System.out.println(matcher.find());
        System.out.println(matcher.find());
        System.out.println(matcher.find());
        System.out.println(matcher.find());
        if (matcher.matches()) {
        
        }
        
        for (String s : List.of("010-12345678", "020-9999999", "0755-7654321")) {
            if (!s.matches(re)) {
                System.out.println("测试失败: " + s);
                return;
            }
            
        }
        for (String s : List.of("010 12345678", "A20-9999999", "0755-7654.321")) {
            if (s.matches(re)) {
                System.out.println("测试失败: " + s);
                return;
            }
        }
        System.out.println("测试成功!");
    }
    
    
    @Test
    public void test4() {
        re = "learn\\s(java|php|go)";
        re = "learn\\s([jJ](ava)|[pP](hp)|[Gg]o)";
        re = "learn\\s((j|J)ava|(p|P)hp|(g|G)o)";
        
        
        System.out.println("learn java".matches(re));
        System.out.println("learn Java".matches(re));
        System.out.println("learn php".matches(re));
        System.out.println("learn Go".matches(re));
        System.out.println("++++++++++++++++++++++++++++++");
        errorTest();
    }
    
    @Test
    public void test5() {
        String input = "23:01:59";
        String regex = "(\\d{2}):(\\d+):(\\d+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        
        //Pattern pattern = Pattern.compile("([0-1][0-9]|2[0-3]):([0-5]?[0-9]):([0-5][0-9])");
        //Matcher matcher = pattern.matcher("23:9:50");
        
        if (matcher.matches()) {
            log.info("字符串匹配: input={}", matcher.group(0));
            log.info("有{}个分组", matcher.groupCount());
            log.info("时:{}, 分:{}, 秒: {}", matcher.group(1), matcher.group(2), matcher.group(3));
            
        }
        log.info("parseTime({})= {}", input, parseTime(input));
    }
    
    public static int[] parseTime(String s) {
        if (s == null || s.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Pattern p = Pattern.compile("(0[0-9]|1[0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])");
        Matcher m = p.matcher(s);
        int[] result = new int[3];
        if (m.matches()) {
            for (int i = 1; i < 4; i++) {
                int time = Integer.parseInt(m.group(i));
                result[i - 1] = time;
            }
        } else throw new IllegalArgumentException();
        return result;
    }
    
    public void errorTest() {
        re = "learn\\s(J|G|j|p)(ava|hp|o)";
        assertEquals(true, "learn Jo".matches(re));
        assertEquals(true, "learn jhp".matches(re));
        assertEquals(true, "learn pava".matches(re));
        assertEquals(true, "learn Gava".matches(re));
        
    }
    
    
    @Test
    public void test6() {
        List<String> strings = Arrays.asList("111", "123000",
                "10100",
                "1001");
        String regex = "\\d+?(0*)";
        Pattern pattern = Pattern.compile(regex);
        for (String string : strings) {
            Matcher matcher = pattern.matcher(string);
            if (matcher.matches()) {
                log.info("字符串{}包含连续0的个数: {}, 分组匹配={}", string, matcher.group(1).length(), matcher.group(1));
            }
        }
    }
    
    @Test
    public void test7() {
        String input = "9999";
        String regex = "(\\d??)(9*)"; // 贪婪与非贪婪
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()) {
            log.info("group(1)={},group(2)={}", matcher.group(1), matcher.group(2));
        }
    }
    
    @Test
    public void testSplit() {
        String[] split = "abc".split("\\s*");
        log.info("数组: {}, 长度: {}", Arrays.toString(split), split.length);
        split = "a b  c".split(" ");
        log.info("数组: {}, 长度: {}", Arrays.toString(split), split.length);
        split = "a, b ;; c".split("[\\s,;]+");
        log.info("数组: {}, 长度: {}", Arrays.toString(split), split.length);
        
    }
    
    /**
     * 返回匹配的内容
     */
    @Test
    public void test8() {
        String content = "<a href=\"/artist/10433/songlist\">";
        System.out.println(content);
        String regex = "/artist/\\d+/songlist";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);
        
        System.out.println(matcher.find());
        System.out.println(matcher.group() + ", " + matcher.group(0));
        System.out.println(content.substring(matcher.start(), matcher.end()));
        matcher.reset();
        if (matcher.find()) {
            for (int i = 0; i <= matcher.groupCount(); i++) {
                log.info(i + ":" + matcher.group(i));
            }
        }
    }
    
    @Test
    public void test9() {
        String regEx = "<ab>([\\s\\S]*?)</a>";
        String s = "<a>123</a><aB>456</a><a>789</a>";
        Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
        Matcher mat = pat.matcher(s);
        //boolean rs = mat.find();
        while (mat.find()) {
            for (int i = 1; i <= mat.groupCount(); i++) {
                System.out.println(mat.group(i));
            }
        }
    }
    
    public static void main(String[] args) {
        
        //String s = "{\n" +
        //        "    \"name\": \"Bob\",\n" +
        //        "    \"lang\": \"Java\"\n" +
        //        "}";
        //
        //s = s.replaceAll("\\s+", "");
        //System.out.println(s);
        //JSONObject jsonObject = JSON.parseObject(s);
        //System.out.println(jsonObject.get("name"));
        //System.out.println(jsonObject.get("lang"));
        
        String input = "Hello, ${name}! You are learning ${lang}!  测试 ${name}  hello ${name } world ${ name   } !!! ";
        Map<String, String> map = new HashMap<>();
        map.put("name", "刘阿勇");
        map.put("lang", "Java");
        
        
        String result = input.replaceAll("\\$\\{name\\}", map.get("name"));
        System.out.println(result);
        result = input.replaceAll("\\$\\{\\s*name\\s*}", map.get("name"));
        System.out.println(result);
        result = result.replaceAll("\\$\\{\\s*lang\\s*}", map.get("lang"));
        System.out.println(result);
        
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            input = input.replaceAll("\\$\\{\\s*" + key + "\\s*}", map.get(key));
            log.info("key=>{}, input={}", key, input);
        }
        System.out.println(input);
    }
}



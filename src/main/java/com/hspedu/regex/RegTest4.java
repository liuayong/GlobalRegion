package com.hspedu.regex;

import com.byd.tool.PrintUtil;
import com.hspedu.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Project: GlobalRegion
 * @Description
 * @Author: Administrator
 * @Create: 2023/3/9
 **/
@Slf4j
public class RegTest4 {
    private static void p(Object o) {
        System.out.println(o);
    }
    
    
    public static void main(String[] args) {
        /**
         * ^	The beginning of a line 一个字符串的开始
         * $	The end of a line       字符串的结束
         * \b	A word boundary         一个单词的边界, 可以是空格, 换行符等
         * 所以在字符串中表示为"\\"
         * 那么如何在正则表达式中表示要匹配\呢, 答案为"\\\\".
         */
        p("hello sir".matches("^h.*"));
        p("hello sir".matches(".*r$"));
        p("hello sir".matches("^h[a-z]{1,3}o\\bsir")); // false
        p("hello sir".matches("^h[a-z]{1,3}o\\b sir")); // true // \b只是匹配单词的边界， 不匹配任何字符
        
        p("hello\tsir".matches("^h[a-z]{1,3}o\\b.*"));
        //FileUtil.allFiles()
    }
    
    
    @Test
    public void test1() {
        p(" \n".matches("^[\\s&&[^\n]]*\\n$")); // 解释: ^[\\s&&[^\n]]*是空格符号但不是换行符, \\n$最后以换行符结束
        p("liuyj24@126.com".matches("[\\w[.-]]+@[\\w[.-]]+\\.[\\w]+"));
        
    }
    
    /**
     * https://juejin.cn/post/6844903825426366471
     */
    @Test
    public void testPos() {
        Pattern pattern = Pattern.compile("\\d{3,5}");
        String s = "123-34345-234-00";
        Matcher m = pattern.matcher(s);
        
        //先演示matches(), 与整个字符串匹配.
        p(m.matches());
        //结果为false, 显然要匹配3~5个数字会在-处匹配失败
        
        //然后演示find(), 先使用reset()方法把当前位置设置为字符串的开头
        m.reset();
        p(m.find());//true 匹配123成功
        p(m.find());//true 匹配34345成功
        p(m.find());//true 匹配234成功
        p(m.find());//false 匹配00失败
        System.out.println("++++++++++++++++++++++++++++");
        //下面我们演示不在matches()使用reset(), 看看当前位置的变化
        m.reset();//先重置
        p(m.matches());//false 匹配整个字符串失败, 当前位置来到-
        p(m.find() + ", " + m.group());// true 匹配34345成功
        p(m.find() + ", " + m.group());// true 匹配234成功
        //p("从开头的位置匹配: " + m.lookingAt());//true 找到123, 成功 改变匹配的位置
        
        boolean o = m.find();
        p(o + ", " + (o ? m.group() : "没有匹配成功"));// false 匹配00始边
        p(m.find());// false 没有东西匹配, 失败
        
        //演示lookingAt(), 从头开始找
        p(m.lookingAt());//true 找到123, 成功
    }
    
    @Test
    public void testPos2() {
        Pattern pattern = Pattern.compile("\\d{3,5}");
        String s = "123-34345-234-00";
        Matcher m = pattern.matcher(s);
        
        p(m.find());//true 匹配123成功
        p("start: " + m.start() + " - end:" + m.end());
        p(m.find());//true 匹配34345成功
        p("start: " + m.start() + " - end:" + m.end());
        p(m.find());//true 匹配234成功
        p("start: " + m.start() + " - end:" + m.end());
        p(m.find());//false 匹配00失败
        try {
            p("start: " + m.start() + " - end:" + m.end());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("报错了...");
        }
        p(m.lookingAt());
        p("start: " + m.start() + " - end:" + m.end());
        
    }
    
    @Test
    public void testReplace() {
        Pattern p = Pattern.compile("java", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher("java Java JAVA JAva I love java and you");
        //p(m.replaceAll("JAVA123"));//replaceAll()方法会替换所有匹配到的字符串
        
        //不区分大小写, 替换查找到的指定字符串， 奇数个字符串转换为大写, 第偶数个转换为小写
        
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (; m.find(); i++) {
            if ((i & 1) == 1) {   // 奇数
                m.appendReplacement(sb, "PHP123");
            } else {
                m.appendReplacement(sb, "php321");
            }
        }
        m.appendTail(sb);
        
        log.info("次数i = {}", i);
        System.out.println(sb);
    }
    
    @Test
    public void readContent() {
        String url = "https://tieba.baidu.com/p/5605167486?red_tag=1108475173";
        url = "D:\\project\\byd\\GlobalRegion\\db\\emailTest2.html";
        BufferedReader br = null;
        List<String> emailList = new ArrayList<>();
        try {
            br = new BufferedReader(new FileReader(url));
            String line = "";
            int lineNum = 1;
            while ((line = br.readLine()) != null) {//读取文件的每一行
                //log.info(String.format("每行: [%d] = {}", lineNum++), line);
                emailList.addAll(parse(line));//解析其中的email地址
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                    br = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        PrintUtil.println(emailList);
    }
    
    @Test
    public void testEmail() {
        String content = "2507827814@qq.com\n" +
                "IP属地:山东来自Android客户端43楼2018-04-14 15:04回复\n" +
                "\n" +
                "聚红为海\n" +
                "白丁1\n" +
                "1606416583@qq.com\n" +
                "\n" +
                "\n" +
                "IP属地:安徽来自Android客户端44楼2018-04-14 23:11回复\n" +
                "\n" +
                "Jhson920\n" +
                "白丁1\n" +
                "1770994588@qq.con";
        
        List<String> parse = parse(content);
        PrintUtil.println(parse);
    }
    
    public static List<String> parse(String line) {
        List<String> emailList = new ArrayList<>();
        String regex = "[\\w-]+@\\w+\\.\\w+";  // "liuayong@qq.com"
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            emailList.add(matcher.group());
        }
        return emailList;
    }
    
    @Test
    // Greedy quantifiers 贪婪模式
    public void avaricious() {
        // 正则表达式的意思是3~10个字符加一个数字. 在贪婪模式下匹配时, 系统会先吞掉10个字符, 这时检查最后一个是否时数字, 发现已经没有字符了, 于是吐出来一个字符, 再次匹配数字, 匹配成功, 得到0-10.
        //Pattern p = Pattern.compile(".{3,20}[0-9]");
        
        // 在非贪婪模式下, 首先只会吞掉3个(最少3个), 然后判断后面一个是否是数字, 结果不是, 在往后吞一个字符, 继续判断后面的是否数字, 结果是, 输出0-5
        Pattern p = Pattern.compile(".{3,10}?[0-9]");//添加了一个?
        
        // 最后演示独占模式, 通常只在追求效率的情况下这么做, 用得比较少
        // 独占模式会一下吞进10个字符, 然后判断后一个是否是数字, 不管是否匹配成功它都不会继续吞或者吐出一个字符.
        //Pattern p = Pattern.compile(".{3,10}+[0-9]");//多了个+
        
        String s = "aaaa5bbbb6";//10个字符
        s = "aaaa5bbbb62";
        Matcher m = p.matcher(s);
        
        int matchCnt = 0;
        while (m.find()) {
            matchCnt++;
            System.out.println(m.start() + " - " + m.end() + " : " + m.group() + "," + m.group().length());
        }
        if (matchCnt == 0) {
            System.out.println("not match!");
        }
    }
    
    @Test
    /**
     Greedy quantifiers 贪婪模式
     Reluctant quantifiers 非贪婪模式(勉强的, 不情愿的)
     Possessive quantifiers  独占模式
     独占模式会一下吞进10个字符, 然后判断后一个是否是数字, 不管是否匹配成功它都不会继续吞或者吐出一个字符.
     */
    public void Possessive() {
        Pattern p = Pattern.compile(".{3,10}+[0-9]");//多了个+
        
        String s = "aaaa5bbbb6";//10个字符
        s = "aaaa5bbbb62hello world88888";
        Matcher m = p.matcher(s);
        int matchCnt = 0;
        while (m.find()) {
            matchCnt++;
            System.out.println(m.start() + " - " + m.end() + " : " + m.group() + "," + m.group().length());
        }
        if (matchCnt == 0) {
            System.out.println("not match!");
        }
    }
    
    @Test
    public void testMatchGroup() {
        //需要截取的字符串
        String splitStr = "[user:name] = select name from user [hell:world]";
        // 定义规则
        String pattern = "(\\[+)((\\w+)\\:(\\w+))(\\]+)";
        // Pattern.compile函数，其中Pattern.CASE_INSENSITIVE代表不区分大小写
        Matcher matcher = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE).matcher(splitStr);
        // matcher.find()执行一次就会匹配一次，比如第一次匹配到了“[user:name]”，第二次匹配到“[hell:world]”
        while (matcher.find()) {
            // matcher.group()代表本次根据规则匹配到的所有内容，本次规则是：\\[+\\w+\\:\\w+\\]+
            // matcher.group(0)代表本次根据规则匹配到的所有内容，本次规则是：\\[+\\w+\\:\\w+\\]+
            // matcher.group(1)代表第1个括号中的规则匹配的内容，本次第1个括号中的规则是：\\[+
            // matcher.group(2)代表第2个括号中的规则匹配的内容，本次第2个括号中的规则是：\\w+\\:\\w+
            // matcher.group(3)代表第3个括号中的规则匹配的内容，本次第3个括号中的规则是：\\w+
            // matcher.group(5)代表第4个括号中的规则匹配的内容，本次第4个括号中的规则是：\\w+
            // matcher.group(4)代表第5个括号中的规则匹配的内容，本次第5个括号中的规则是：\\]+
            System.out.println(matcher.group() + "   " + matcher.group(0) + "   " + matcher.group(1)
                    + "   " + matcher.group(2) + "   " + matcher.group(3) + "   " + matcher.group(4) + "   " + matcher.group(5));
        }
        
        pattern = "(?:\\[+)((?<key>\\w+)\\:(?<val>\\w+))(?:\\]+)";
        matcher = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE).matcher(splitStr);
        // matcher.find()执行一次就会匹配一次，比如第一次匹配到了“[user:name]”，第二次匹配到“[hell:world]”
        while (matcher.find()) {
            System.out.println(matcher.groupCount());
            System.out.println(matcher.group() + "   " + matcher.group(0) + "   " + matcher.group(1)
                    + "   " + matcher.group(2) + "   " + matcher.group(3)
                    + "   key:  " + matcher.group("key") + "   val:" + matcher.group("val"));
        }
    }
    
}

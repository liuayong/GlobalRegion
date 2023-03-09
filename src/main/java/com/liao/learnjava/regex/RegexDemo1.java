package com.liao.learnjava.regex;

import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertTrue;

@Slf4j
public class RegexDemo1 {
    
    @Test
    public void test1() {
        // 与定义字符串
        String reg1 = "\\w";    // 单词字符 包含_ 不包含-,中文汉字
        String reg2 = ".";      // 包含中文汉字
        String reg3 = "\\W";
        // \d <==> [^0-9]
        
        List<String> list = Arrays.asList("中", "-", "_", "3", "A");
        for (String s : list) {
            log.info("s.matches(reg1) = {}", s.matches(reg1));
            log.info("s.matches(reg2) = {}", s.matches(reg2));
            log.info("s.matches(reg3) = {}", s.matches(reg3));
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++\n");
        }
        
    }
    
    
    @Test
    public void test2() {
        String input = "Hello, ${name}! You are learning ${lang}  ${ok}! ";
        Map<String, Object> data = new HashMap<>();
        data.put("name", "Bob");
        data.put("lang", "Java");
        data.put("ok", null);
        
        String regex = "\\$\\{(\\w+)}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            String key = matcher.group(1);
            Object o = data.get(key);
            if (o != null) {
                matcher.appendReplacement(sb, o.toString());
            }
        }
        matcher.appendTail(sb);
        log.info("sb = {}\ninput={}\n", sb, input);
        
        String replace = input;
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            Object value = entry.getValue();
            if (value != null) {
                replace = replace.replaceAll("\\$\\{" + entry.getKey() + "\\}", value.toString());
            }
        }
        log.info("replace = {}\ninput={}\n", sb, input);
        
    }
    
    @Test
    public void test3() {
        String regex = "1[0-9&&[^014]]\\d{9}";
        String[] phones = {"13718526927", "10212341234"};
        for (String anPhone : phones) {
            boolean match1 = anPhone.matches(regex);
            boolean match2 = isPhoneNumber(anPhone);
            log.info("正则匹配={}, 传统验证={}", match1, match2);
        }
    }
    
    @Test
    public void test5() {
        String greet1 = "hello world";
        String greet2 = "hello";
        String regex = "hello";
        log.info("greet1:{}, greet2:{}", greet1.matches(regex), greet2.matches(regex));
        
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE); // 不区分大小写 Pattern, 默认是区分大小写的
        // public boolean find() //尝试查找与该模式匹配的输入序列的下一个子序列。
        //log.info("greet1:{}, greet2:{}", pattern.matcher(greet1).find(), pattern.matcher(greet2).find());
        
        // public boolean lookingAt()  尝试将从区域开头开始的输入序列与该模式匹配。
        log.info("greet1:{}, greet2:{}", pattern.matcher(greet1).lookingAt(), pattern.matcher(greet2).lookingAt());
    }
    
    @Test
    public void test6() {
        String greet1 = "hello world";
        String greet2 = "hello";
        String regex = "^hello$";
        log.info("greet1:{}, greet2:{}", greet1.matches(regex), greet2.matches(regex));
        
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE); // 不区分大小写 Pattern, 默认是区分大小写的
        // public boolean find() //尝试查找与该模式匹配的输入序列的下一个子序列。
        //log.info("greet1:{}, greet2:{}", pattern.matcher(greet1).find(), pattern.matcher(greet2).find());
        
        // public boolean lookingAt()  尝试将从区域开头开始的输入序列与该模式匹配。
        log.info("greet1:{}, greet2:{}", pattern.matcher(greet1).lookingAt(), pattern.matcher(greet2).lookingAt());
        
        
    }
    
    @Test
    public void test7() {
        String content = "I am noob " +
                "from runoob.com.";
        
        String pattern = ".*runoob.*";
        //pattern = "runoob";
        
        boolean isMatch = Pattern.matches(pattern, content);
        log.info("字符串中是否包含了 'runoob' 子字符串?  {}, {}", isMatch, content.matches(pattern));
        
        
    }
    
    @Test
    public void test8() {
        // 按指定模式在字符串查找
        String line = "This order was placed for QT3000! OK? laced for QT123! O";
        String pattern = "(\\D*)(\\d+)(!\\s*.*?)";
        //pattern = "(\\D*)(\\d+)(!)";
        Pattern compile = Pattern.compile(pattern);
        Matcher matcher = compile.matcher(line);
        int i = matcher.groupCount();
        log.info("捕获组 {}", i);
        while (matcher.find()) {
            String group2 = matcher.group(2);
            String group3 = matcher.group(3);
            log.info("group2 {}", group2);
            log.info("group3 {} , length={}\n", group3, group3.length());
        }
    }
    
    /**
     * todo 刘阿勇
     */
    @Test
    public void test9() {
        String input = "Hello,Windows 2000!!!, Hello,Windows NT, NT Good Windows 98 is better than Windows 95 !!!";
        String regex = "Windows (?=95|98|NT|2000)";
        //regex = "Windows (?=2000|95)";
        Pattern compile = Pattern.compile(regex);
        Matcher matcher = compile.matcher(input);
        
        log.info("match1 = {}", input.matches(regex));
        //log.info("match2 = {}", matcher.matches()); // 会改变搜索的起始位置
        //matcher.reset();
        log.info("match3 = {}", Pattern.matches(regex, input));
        log.info("match4 全局匹配 = {}", matcher.find());
        
        matcher.reset();
        
        while (matcher.find()) {
            //System.out.println(matcher.groupCount());
            log.info("{} , {}", matcher.group(), matcher.group(0));
        }
        
    }
    
    /*
    零宽断言的意思是（匹配宽度为零，满足一定的条件/断言） 我也不知道这个词语是那个王八蛋发明的，简直是太拗口了。

零宽断言用于查找在某些内容(但并不包括这些内容)之前或之后的东西，也就是说它们像 \b ^ $ \< \> 这样的锚定作用，仅仅用于指定一个位置，不参与内容匹配，这个位置应该满足一定的条件(即断言)，因此它们也被称为零宽断言。 断言用来声明一个应该为真的事实。正则表达式中只有当断言为真时才会继续进行匹配。

    (?=表达式) 零宽度正预测先行断言 表示匹配表达式前面的位置
    http://www.ibloger.net/article/31.html
    例如，要匹配 cooking ，singing ，doing中除了ing之外的内容，只取cook, sing, do的内容，这时候的增则表达式可以用 [a-z]*(?=ing) 来匹配

注意：先行断言的执行步骤是这样的先从要匹配的字符串中的最右端找到第一个 ing (也就是先行断言中的表达式)然后 再匹配其前面的表达式，
若无法匹配则继续查找第二个 ing 再匹配第二个 ing 前面的字符串，若能匹配则匹配，符合正则的贪婪性。
例如： .*(?=ing) 可以匹配 “cooking singing” 中的 “cooking sing” 而不是 cook
     */
    @Test
    public void test9_2() {
        String input = "cooking ，singing ，doing";
        String regex = "\\w+?(?=ing)";
        Pattern compile = Pattern.compile(regex);
        Matcher matcher = compile.matcher(input);
        while (matcher.find()) {
            log.info("{}", matcher.group(0));
        }
    }
    
    /**
     * 实战1：匹配不包含属性的HTML标签里的内容
     */
    @Test
    public void test11() {
        String input = "<div class=\"container\">\n" +
                "      <div>\n" +
                "        <h1>Bootstrap starter template</h1>\n" +
                "\t    <?= printTable(mergeDetailList($order), '将明细数据合并到主数据 方法mergeDetailList'); ?>\n" +
                "\t    <?= printTable(transDetails($order), '明细数据列转行 方法mergeDetailList'); ?>\n" +
                "      </div>\n" +
                "    </div><!-- /.container -->";
        String regex = "(?<=<([\\w]{1,20})>).*(?=<\\/\\1>)";
        Pattern compile = Pattern.compile(regex);
        Matcher matcher = compile.matcher(input);
        while (matcher.find()) {
            String group0 = matcher.group(0);
            int count = matcher.groupCount();
            log.info("group0 {}", group0);
            log.info("count {}", count);
        }
    }
    
    /*
    // 从内容上截取路径数组
        Pattern pattern = Pattern.compile("(?<=\\()[^\\)]+");
     */
    @Test
    public void test12() {
        String content = "src: local('Open Sans Light'), local('OpenSans-Light'), url(http://fonts.gstatic.com/s/opensans/v13/DXI1ORHCpsQm3Vp6mXoaTa-j2U0lmluP9RWlSytm3ho.woff2) format('woff2')";
        String regex = "(?<=\\()([^)]+)";
        regex = "(?<=url\\()[^\\)]+"; // 匹配URL中的链接地址
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            log.info("{}", matcher.group(0));
        }
        System.out.println("++++++++++++++++++++++++++++++++++++\n");
        test12_2();
    }
    
    public void test12_2() {
        String content = "src: local('Open Sans Light'), local('OpenSans-Light'), url(http://fonts.gstatic.com/s/opensans/v13/DXI1ORHCpsQm3Vp6mXoaTa-j2U0lmluP9RWlSytm3ho.woff2) format('woff2')";
        // 从内容上截取路径数组
        Pattern pattern = Pattern.compile("(?<=\\()[^\\)]+");
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
    
    public boolean isPhoneNumber(String phone) {
        if (phone == null || phone.length() != 11) {
            return false;
        }
        char[] chars = phone.toCharArray();
        if (chars[0] != '1') {
            return false;
        }
        // 是否在数组里面
        char[] chars2 = {'2', '3', '5', '6', '7', '8', '9'};
        int pos = Arrays.binarySearch(chars2, chars[1]);
        log.info("pos={}", pos);
        boolean contains = ArrayUtils.contains(chars2, chars[1]);
        log.info("contains1={}, contains2={}", contains, Arrays.asList(chars2).contains(chars[1]));
        if (!contains) {
            return false;
        }
        for (int i = 2; i < chars.length; i++) {
            char ch = chars[i];
            if (ch < '0' || ch > '9') {
                return false;
            }
        }
        return true;
    }
    
    
    @Test
    public void testIp() {
        // \b((25[0-5]|2[0-4]\d|[01]?\d\d?)\.){3}(25[0-5]|2[0-4]\d|[01]?\d?\d)\b
        String regex = "((25[0-5]|2[0-4]\\d|[01]?\\d?\\d)\\.){3}(25[0-5]|2[0-4]\\d|[01]?\\d?\\d)";
        String[] ips = {
                "0.0.0.0",
                "10.1.3.2",
                "192.168.1.105",
                "255.255.255.0",
                "3.6.251.0",
                "3.6.22$0",
        };
        
        for (String ip : ips) {
            if (!ip.matches(regex)) {
                log.error("ip ={}", ip);
            }
            //assertTrue(ip.matches(regex));
        }
    }
    
    /**
     * 匹配中文 https://blog.csdn.net/qq_42449963/article/details/116900525
     */
    @Test
    public void test4() {
        String regex = "a\\u548cB";  // unicode --> char
        System.out.println(regex);
        String input = "a和B";
        log.info("matches={}, equals={}", input.matches(regex), input.equals(regex));
        
        int code = Integer.parseInt("548c", 16);
        System.out.println("code = " + code + ", 转为中文字符: " + (char) code);
    }
    
    @Test
    public void test10() {
        String REGEX = "dog";
        String INPUT = "The dog says meow. " +
                "All dogs say meow.";
        String origin = INPUT;
        String REPLACE = "CAT";
        
        Pattern p = Pattern.compile(REGEX);
        // get a matcher object
        Matcher m = p.matcher(INPUT);
        
        
        String result = m.replaceFirst(REPLACE);
        System.out.println(result);
        
        
        StringBuffer sb = new StringBuffer();
        m.reset();
        while (m.find()) {
            m.appendReplacement(sb, REPLACE);
        }
        m.appendTail(sb);
        log.info("sb = {} origin= {}\n", sb, origin);
        
        log.info("String replaceFirst = {}", origin.replaceFirst(REGEX, REPLACE));
        log.info("String replaceAll() = {}", origin.replaceAll(REGEX, REPLACE));
    }
    
    @Test
    public void test15() {
        String REGEX = "a*b"; // a出现0次或多次
        String INPUT = "aabfooaabfooabfoobkkk";
        String REPLACE = "-";
        Pattern p = Pattern.compile(REGEX);
        // 获取 matcher 对象
        Matcher m = p.matcher(INPUT);
    
        //System.out.println(m.find());
        
        
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            log.info("{}", m.group());
            m.appendReplacement(sb, REPLACE);
        }
        m.appendTail(sb);
        System.out.println(sb.toString());
    }
    
}

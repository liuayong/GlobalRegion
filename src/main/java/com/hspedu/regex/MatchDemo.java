package com.hspedu.regex;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class MatchDemo {
    @Test
    public void mai1n() {
        String content = "I am noob " +
                "from runoob.com.";
        
        String pattern = ".*(runoob).*";
        
        boolean isMatch = Pattern.matches(pattern, content);
        System.out.println("isMatch = " + isMatch);
        Pattern r = Pattern.compile(pattern);
        Matcher matcher = r.matcher(content);
        System.out.println(matcher.groupCount());
        
        System.out.println();
    }
    
    
    @Test
    public void demo3() {
        // 按指定模式在字符串查找
        String line = "This order was placed for QT3000! OK?你好\n世界This order was placed for QT3000! OK?";
        String pattern = "(\\D*)(\\d+)(.*)";
        
        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);
        // 现在创建 matcher 对象
        Matcher m = r.matcher(line);
        System.out.println(m.groupCount());
        while (m.find()) {
            System.out.println("Found value: " + m.group(0));
            System.out.println("Found value: " + m.group(1));
            System.out.println("Found value: " + m.group(2));
            System.out.println("Found value: " + m.group(3));
            System.out.println("==========================================");
        }
    }
    
    @Test
    public void demo2() {
        String fileContent = "BizException e1 = new BizException(e, ErrorCodeConstant.QUERY_FAILURE_CODE, \"出库信息查询\");";
        
        String b = "我是B哈哈 BizException e1 = new BizException(ErrorCodeConstant.ADD_FAILURE_CODE, \"添加学校出错\");";
        fileContent += " 你好啊 " + b;
        
        // 括号表示组，被替换的部分$n表示第n组的内容
        String regex = "BizException\\s*\\([^\"]+\\s*,\\s*(\"\\w*[\\u4e00-\\u9fa5\\/!.。！]+\\w*\")\\s*\\)";
        //fileContent = fileContent.replaceAll(regex, "###$1###");
        //System.out.println(fileContent);
        
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(fileContent);
        
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "#AA#");
        map.put(2, "@BB@");
        map.put(3, "$CC$");
        
        int count = 0;
        while (matcher.find()) {
            count++;
            
            System.out.println(Objects.equals(matcher.group(), matcher.group(0)));
            String tipMsg = matcher.group(1);
            System.out.println(tipMsg);
            String replace = matcher.group(0).replaceAll("\"\\w*[\\u4e00-\\u9fa5\\/!.。！]+\\w*\"", "AA");
            System.out.println(matcher.group(0));
            System.out.println(replace);
            
            fileContent = fileContent.replaceFirst("(?<=\")\\w*[\\u4e00-\\u9fa5\\/!.。！]+\\w*(?=\")", map.get(count));
            System.out.println(fileContent + "| ");
            
        }
        
        System.out.println("count = " + count);
        System.out.println(fileContent + "| ");
        
        
        // 期望替换之后 就是在中文字符串前后加上###的标记 如下
        // 期望变为 BizException e1 = new BizException(e, ErrorCodeConstant.QUERY_FAILURE_CODE, \"###出库信息查询###\");
        
        
        /*
        //下面是js代码， 直接在浏览器控制台可以执行
        var fileContent = "BizException e1 = new BizException(e, ErrorCodeConstant.QUERY_FAILURE_CODE, \"出库信息查询\");";
        var regex = /BizException\s*\([^"]+\s*,\s*"(\w*[\u4e00-\u9fa5\/!.。！]+\w*)"\s*\)/;
        var fileContent2 = fileContent.replace(regex, "###$1###");
        console.log(fileContent)
        console.log(fileContent2)
        */
    }
    
    
    /**
     * http://www.zzvips.com/article/127309.html
     */
    @Test
    public void demo1() {
        String text = "13522158842;托尔18212349545斯泰;test2;13000002222;8613111113313222";
        //Pattern pattern = Pattern.compile("(?<!\\d)(?:(?:1[358]\\d{9})|(?:861[358]\\d{9}))(?!\\d)");
        Pattern pattern = Pattern.compile("\\d{11}");
        Matcher matcher = pattern.matcher(text);
        StringBuffer bf = new StringBuffer(164);
        while (matcher.find()) {
            bf.append(matcher.group()).append(",");
        }
        int len = bf.length();
        if (len > 0) {
            bf.deleteCharAt(len - 1);
        }
        System.out.println(bf.toString());
        // 13522158842,18212349545,13000002222,8613111113313
    }
    
    /**
     * 正则将小括号里面的内容匹配出来
     */
    @Test
    public void test3() {
        String content = "src: local('Open Sans Light'), local('OpenSans-Light'), url(http://fonts.gstatic.com/s/opensans/v13/DXI1ORHCpsQm3Vp6mXoaTa-j2U0lmluP9RWlSytm3ho.woff2) format('woff2')";
        String regex = "(?<=\\w{1,20}\\()([^)]+)";
        regex = "(\\w+)\\((?<tag>[^)]+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);
        
        System.out.println(matcher.groupCount());
        while (matcher.find()) {
            log.info("string={}, match1={}, match2={}, count= {}", matcher.group(), matcher.group(1), matcher.group(2),
                    matcher.groupCount());
            log.info("捕获组别名获取值 {}, 分组编号获取值 {}", matcher.group("tag"), matcher.group(2));
            if (matcher.group(1).equals("url")) {
                log.info("匹配到的url = {}", matcher.group(2));
            }
        }
        
        regex = "(?<=url\\()(?<url>[^)]+)";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(content);
        if (matcher.find()) {
            log.info("命名捕获组 {}\n序号捕获组 {}", matcher.group("url"), matcher.group(1));
        }
    }
    
    
    /**
     *
     */
    @Test
    public void test4() {
        String text = "hello   String text = \"2021-12-31\"; world 2023-3-5 05:40:51";
        String regex = "(\\d{4})-(\\d{1,2})-(\\d{1,2})";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            log.info("分组数量: {}", matcher.groupCount());
            log.info("{}年-{}月-{}日", matcher.group(1), matcher.group(2), matcher.group(3));
        }
        
        // 华丽的分割线
        System.out.println("+++++++++++++++++++++++++++命名捕获组++++++++++++++++++++++++++++++++");
        regex = "(?<year>\\d{4})-(?<month>\\d{1,2})-(?<day>\\d{1,2})";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(text);
        while (matcher.find()) {
            log.info("分组数量: {}", matcher.groupCount());
            log.info("{}年-{}月-{}日", matcher.group(1), matcher.group(2), matcher.group(3));
            log.info("{}年-{}月-{}日", matcher.group("year"), matcher.group("month"), matcher.group("day"));
        }
    }
    
    /**
     *
     */
    @Test
    public void test5() {
        String text = "John writes about this, and John Doe writes about that, and John Wayne writes about everything.";
        String patternString1 = "((John) (.+?)) ";    // 非贪婪模式
        //patternString1 = "((John) (\\S+))";    // 贪婪模式
        Pattern pattern = Pattern.compile(patternString1);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            // group1: "((John) (.+?)) " Json 字符串 加上下一个字符串直到空格结尾
            // group2: (John)
            System.out.println("found: <" + matcher.group(1) + "> <" + matcher.group(2) + "> <" + matcher.group(3) + ">");
        }
    }
    
    
    /**
     * 零宽断言
     */
    @Test
    public void test6() {
        String text = "例如，要匹配 cooking ，singing ，doing中除了ing之外的内容，" +
                "只取cook, sing, do的内容，这时候的增则表达式可以用";
        String regex = "\\w+(?=ing)";
        regex = "[a-z]+?(?=ing)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            log.info("匹配 {}", matcher.group());
        }
    }
    
}

package com.hspedu.regex;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatchDemo {
    
    public static void main(String[] args) {
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
            
            fileContent = fileContent.replaceFirst("\"\\w*[\\u4e00-\\u9fa5\\/!.。！]+\\w*\"", map.get(count));
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
    private static void demo1() {
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
}

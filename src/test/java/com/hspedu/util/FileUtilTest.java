package com.hspedu.util;

import com.byd.tool.PrintUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

/**
 * @Project: GlobalRegion
 * @Description
 * @Author: Administrator
 * @Create: 2023/3/4
 **/
@Slf4j
public class FileUtilTest {
    
    @Test
    public void allFiles() {
        String path = "D:\\project\\byd\\GlobalRegion\\src\\main\\java\\com\\liao\\learnjava";
        
        File file = new File(path);
        List<String> files = FileUtil.allFiles(file);
        System.out.println("文件数量: " + files.size());
        // 打印List的数量，  最多打印3个
        //List<String> subList = files.subList(0, Math.min(3, files.size()));
        for (String f : files) {
            System.out.println(f);
        }
    }
    
    
    @Test
    public void testNewLine() {
        //String regex = "[A-Z]{4}(?!\\S)" ;// "\\b[A-Z]{4}\\b";
        //String regex = "[A-Z]{4}(?=\\n)" ; // 以换行符结尾，但是又不匹配换行符
        String regex = "[A-Z]{4}\\n$"; // 以换行符结尾
        Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        String input = "ABCD BCDE GHT GHJL\n" +
                "XSE EFAB BHUI ABCE\n";
        Matcher matcher = pattern.matcher(input);
        // check all occurance
        while (matcher.find()) {
            System.out.println(matcher.group().trim()); // todo sdfsdf
        }
    }
    
    @Test
    public void replaceToDo() {
        String str = "测试正则: " +
                "        // TODO Auto-generated method stub\n" +
                "        Gson gson2 = new Gson();\n" +
                "        Map<String, String> maps = gson.fromJson(json, new TypeToken<Map<String, String>>() { // " +
                "todo sdfsdf33   \n" +
                "        }.getType());\\n";
        
        String regex = "//\\s+todo\\s+(.*)";       // 以换行符为结尾
        //String replace = str.replaceAll(regex, "");
        //System.out.println(replace);   System.exit(0);
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            log.info("input={},group1={}", matcher.group(), matcher.group(1));
            matcher.appendReplacement(sb, "");
        }
        matcher.appendTail(sb);
        
        //matcher.reset();
        //String replace = str.replaceAll(regex, "");
        //System.out.println(replace);
        
        System.out.println(sb);
    }
    
    @Test
    public void testPath() {
        String filePath = "D:\\project\\byd\\GlobalRegion\\db\\sql";
        
        FileUtil.process(filePath);
    
        List<String> fileList = FileUtil.allFiles(new File(filePath));
        PrintUtil.println(fileList);
    }
    
    
}
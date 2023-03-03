package com.hspedu.util;

import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Project: GlobalRegion
 * @Description
 * @Author: Administrator
 * @Create: 2023/3/4
 **/
public class FileUtilTest {
    
    @Test
    public void allFiles() {
        String path = "D:\\project\\byd\\GlobalRegion\\src\\main\\java\\com\\liao\\learnjava";
        
        File file = new File(path);
        List<String> files = FileUtil.allFiles(file);
        System.out.println("文件数量: " + files.size());
        //List<String> subList = files.subList(0, Math.min(3, files.size()));
        for (String f : files) {
            System.out.println(f);
        }
    }
}
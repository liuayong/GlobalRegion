package com.hspedu.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @Project: GlobalRegion
 * @Description
 * @Author: Administrator
 * @Create: 2023/3/4
 **/
public class FileUtil {
    
    
    /**
     * 递归列出某个文件夹下的文件列表
     *
     * @param file
     * @return
     */
    public static List<String> allFiles(File file) {
        File[] files = file.listFiles();
        List<String> fileList = new ArrayList<>();
        assert files != null;
        for (File itFile : files) {
            if (itFile.isDirectory()) {
                fileList.addAll(allFiles(itFile));
            } else {
                fileList.add(itFile.getAbsolutePath());
            }
        }
        return fileList;
    }
    
}

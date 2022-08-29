package com.hspedu.io;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LookFileTest {
    public static void main(String[] args) {
        String path = "D:\\project\\byd\\GlobalRegion\\src\\main\\java\\com\\hspedu";
        File file = new File(path);
        List<String> files = allFiles2(file);
        
        System.out.println("文件数量: " + files.size());
        for (String f : files) {
            System.out.println(f);
        }
    }
    
    private static List<String> allFiles2(File file) {
        //File file = new File(path);
        //String[] list = file.list();
        
        File[] files = file.listFiles(new FileFilterImpl());
        List<String> fileList = new ArrayList<>();
        for (File itFile : files) {
            if (itFile.isDirectory()) {
                fileList.addAll(allFiles2(itFile));
            } else {
                fileList.add(itFile.getAbsolutePath());
            }
        }
        return fileList;
    }
    
    private static List<String> allFiles1(File file) {
        //File file = new File(path);
        //String[] list = file.list();
        
        File[] files = file.listFiles();
        List<String> fileList = new ArrayList<>();
        for (File itFile : files) {
            if (itFile.isDirectory()) {
                fileList.addAll(allFiles1(itFile));
            } else if (!itFile.getName().toLowerCase().endsWith(".java")) {
                fileList.add(itFile.getAbsolutePath());
            }
        }
        return fileList;
    }
}

package com.hspedu.io;

import com.byd.tool.PrintUtil;
import com.hspedu.util.StreamUtil;
import com.hspedu.util.StringUtil;
import lombok.SneakyThrows;
import org.junit.Test;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @Project: GlobalRegion
 * @Description
 * @Author: Administrator
 * @Create: 2023/4/22
 **/

public class FileTest {
    @Test
    public void test0() {
        String path = "D:\\project\\byd\\GlobalRegion\\doc\\test\\r1.txt";
        String path2 = "doc\\test\\r1.txt";
        
        File file = new File(path);
        File file2 = new File(path2);
        System.out.println(file.getAbsolutePath());
        System.out.println(file2.getAbsolutePath());
    }
    
    @Test
    public void test1() {
        File f3 = new File("..\\sub\\javac"); // 绝对路径是C:\sub\javac
        
        System.out.println(f3.getAbsolutePath());
        System.out.println(f3.isFile());
        System.out.println(f3.isDirectory());
        
        System.out.println(f3.getParentFile());
        System.out.println(f3.getAbsoluteFile());
        
    }
    
    @SneakyThrows
    @Test
    public void test2() {
        File f = new File("..");
        System.out.println(f.getPath());
        System.out.println(f.getAbsolutePath());
        System.out.println(f.getCanonicalPath());
        System.out.println(File.separator);
        System.out.println(f.length());
        
        
    }
    
    @Test
    public void test3() throws IOException {
        File f = File.createTempFile("tmp-", ".txt"); // 提供临时文件的前缀和后缀
        f.deleteOnExit(); // JVM退出时自动删除
        System.out.println(f.isFile());
        System.out.println(f.getAbsolutePath());
        System.out.println(f.getCanonicalPath());
        
    }
    
    
    private void printFiles(File[] files) throws IOException {
        if (files != null) {
            for (File f : files) {
                System.out.println(f.getCanonicalPath());
            }
            System.out.println(String.format("数量++++++++++++ %d +++++++++++++\n", files.length));
        }
    }
    
    @Test
    public void test4() throws IOException {
        Path p1 = Paths.get(".", "project/", "study\\"); // 构造一个Path对象
        System.out.println(p1);
        Path p2 = p1.toAbsolutePath(); // 转换为绝对路径
        System.out.println(p2);
        Path p3 = p2.normalize(); // 转换为规范路径
        System.out.println(p3);
        
        File f = p3.toFile(); // 转换为File对象
        System.out.println(f);
        System.out.println(f.getCanonicalPath());
        System.out.println(p1.toFile().getCanonicalPath());
        
        //Path path1 = Paths.get("..");
        //Path path = p1.toAbsolutePath();
        System.out.println("+++++++++++++++++++++++++++++");
        
        for (Path path2 : p1) {
            System.out.println(path2.toFile().getCanonicalPath());
        }
        System.out.println("+++++++++++++++++++++++++++++");
        for (Path p : Paths.get("..").toAbsolutePath()) { // 可以直接遍历Path
            System.out.print(p + "  ");
            System.out.println(p.normalize());
        }
    }
    
    @Test
    public void test5() throws IOException {
        
        for (Path p : Paths.get("..").toAbsolutePath()) { // 可以直接遍历Path
            System.out.print(p + "  ");
            System.out.println(p.normalize());
        }
    }
    
    
    @Test
    public void test6() throws IOException {
        String path = "D:\\project\\byd\\GlobalRegion\\src\\main\\java\\com\\liao\\learnjava";
        
        List<String> files = new ArrayList<>();
        listFiles(path, files);
        for (int i = 0, size = files.size(); i < size; i++) {
            System.out.println(files.get(i));
        }
        
        
        String filePath = "src\\main\\java\\com\\liao\\learnjava\\io\\CountInputStream.java";
        // D:\project\byd\GlobalRegion\src\main\java\com\liao\learnjava\io\CountInputStream.java
        File file = new File(filePath);
        System.out.println(file.getCanonicalPath());
        System.out.println("是否为文件: " + file.isFile() + ", 是否为目录: " + file.isDirectory());
        File[] files1 = file.listFiles();
        System.out.println("files1 = " + files1);
    }
    
    private static int depth = 0; //计算目录层级数
    
    
    private void listFiles(String path, List<String> files) {
        File file = new File(path);
        String ind = StringUtil.repeat("　　", depth);
        if (file.isDirectory()) {
            files.add(ind + file.getName());
            File[] files1 = file.listFiles();
            depth++;
            for (File file1 : files1) {
                listFiles(file1.getAbsolutePath(), files);
            }
            depth--;
        } else {
            files.add(ind + file.getName());
        }
    }
    
    public static void main(String[] args) throws IOException {
        String path = "D:\\project\\byd\\GlobalRegion\\src\\main\\java\\com\\liao\\learnjava";
        
        File currentDir = new File(path);
        listDir(currentDir.getCanonicalFile());
    }
    
    static void listDir(File dir) {
        // TODO: 递归打印所有文件和子文件夹的内容
        File[] fs = dir.listFiles();
        if (fs != null) {
            for (File f : fs) {
                System.out.println(f.getName());
            }
        }
    }
}
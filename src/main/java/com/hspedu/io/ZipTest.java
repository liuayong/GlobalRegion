package com.hspedu.io;

import com.byd.tool.PrintUtil;
import org.junit.Test;
import org.springframework.util.StopWatch;

import java.io.*;
import java.nio.file.Files;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @Project: GlobalRegion
 * @Description
 * @Author: Administrator
 * @Create: 2023/4/26
 **/
public class ZipTest {
    
    public static void main(String[] args) throws IOException {
        //// TODO Auto-generated method stub
        //File source = new File("D:\\Java\\demo");
        //File target = new File("D:\\Java\\demo.zip");
        //toZip(source, target);
        //Desktop.getDesktop().open(target);
        
        
        String path = "D:\\project\\byd\\GlobalRegion\\src\\main\\java\\com\\mexue\\middle\\school";
        File target1 = new File("D:\\Java\\demo1.zip");
        // source 是一个目录
        File source1 = new File(path);
        System.out.println("是否为文件: " + source1.isFile() + ", 是否为目录: " + source1.isDirectory());
        toZip(source1, target1);
        
        String path2 = "D:\\project\\byd\\GlobalRegion\\src\\main\\java\\com\\mexue\\middle\\school\\common\\PageResult.java";
        File target2 = new File("D:\\Java\\demo2.zip");
        // source 是一个文件
        File source2 = new File(path2);
        System.out.println("是否为文件: " + source2.isFile());
        toZip(source2, target2);
    }
    
    
    public static void toZip(File source, File target) throws IOException {
        try (ZipOutputStream outPut = new ZipOutputStream(new FileOutputStream(target))) {
            if (source.isDirectory()) {
                File[] files = source.listFiles();
                for (File file : Objects.requireNonNull(files)) {
                    zipTools(file, source, outPut);
                    outPut.closeEntry();
                }
            } else {
                zipTools(source, source.getParentFile(), outPut);
                outPut.closeEntry();
            }
            System.out.println(target + "打包结束！");
        }
    }
    // 递归函数
    
    public static void zipTools(File file, File source, ZipOutputStream outPut) throws IOException {
        
        if (file.isFile()) {
            outPut.putNextEntry(new ZipEntry(getRelativePath(file, source)));
            outPut.write(Files.readAllBytes(file.toPath()));
        } else {
            outPut.putNextEntry(new ZipEntry(getRelativePath(file, source) + File.separator));
            for (File subFile : Objects.requireNonNull(file.listFiles())) {
                file = subFile;
                zipTools(subFile, source, outPut);
            }
        }
    }
    
    
    //获得相对路径
    
    public static String getRelativePath(File childFile, File parentFile) {
        
        return childFile.getPath().substring(parentFile.getPath().length() + 1);
        
    }
    
    /**
     * 准确地说：
     * <p>
     * byte[] data = new byte[(int)f.length()];
     * try(InputStream is =new FileInputStream(f))
     * 你这两行代码之间，有可能文件会被其他程序修改，导致实际读取的字节与文件长度不一致
     * <p>
     * 用ByteArrayOutputStream，那个就是用来在内存中开辟缓存的。
     */
    public static byte[] getFileDataAsBytesV0(File f) {
        byte[] data = new byte[(int) f.length()];
        try (InputStream is = new FileInputStream(f)) {
            int n;
            while ((n = is.read(data)) != -1) {
                System.out.println("read " + n + " bytes");
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
    
    public static byte[] getFileDataAsBytesV1(File File) throws IOException {
        
        byte[] bytes;
        try (InputStream input = new FileInputStream(File);
             ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            int n;
            while ((n = input.read()) != -1) {
                output.write(n);
            }
            ;
            bytes = output.toByteArray();
        }
        //System.out.println(bytes.length);
        return bytes;
    }
    
    public static byte[] getFileDataAsBytesV2(File File) throws IOException {
        
        byte[] bytes;
        try (InputStream input = new FileInputStream(File);
             ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            int n;
            byte[] buffer = new byte[2048];
            while ((n = input.read(buffer)) != -1) {
                output.write(buffer, 0, n);
            }
            bytes = output.toByteArray();
        }
        //System.out.println(bytes.length);
        return bytes;
    }
    
    @Test
    public void testFileToBytes() throws IOException {
        String path = "D:\\java\\黑马javeee视频教程云计算与大数据教程12期\\临时记录.docx";
        File file = new File(path);
        byte[] fileDataAsBytes2 = getFileDataAsBytesV1(file);
        PrintUtil.println(fileDataAsBytes2.length);
        //byte[] fileDataAsBytesParent = getFileDataAsBytes2(file.getParentFile());
        //PrintUtil.println(fileDataAsBytesParent);
        
        //InputStream in = new FileInputStream(file.getParentFile());
        InputStream in = new FileInputStream(new File("D:\\java\\黑马javeee视频教程云计算与大数据教程12期"));
        System.out.println(in.available());
    }
    
    @Test
    public void testBigFile() throws IOException {
        String path = "C:\\Java\\Spring Boot开发一个小而美的个人博客\\Spring Boot开发一个小而美的个人博客\\4. 框架搭建\\1. 构建框架.mp4"; // 60.2 MB (63,170,591 字节)
        path="C:\\Java\\Spring Boot开发一个小而美的个人博客\\Spring Boot开发一个小而美的个人博客.rar";  // 3.08 GB (3,309,159,166 字节)
        File file = new File(path);
        long start = System.currentTimeMillis();
        StopWatch stopWatch = new StopWatch("测试大文件转二进制");
        
        //stopWatch.start("fileDataAsBytesV0");
        //// java.lang.NegativeArraySizeException: -985808130
        //byte[] fileDataAsBytesV0 = getFileDataAsBytesV0(file);
        //PrintUtil.println(fileDataAsBytesV0.length);
        //stopWatch.stop();
        
        
        // 一个字节，一个字节拷贝非常慢
        //stopWatch.start("fileDataAsBytesV1");
        //byte[] fileDataAsBytesV1 = getFileDataAsBytesV1(file);
        //PrintUtil.println(fileDataAsBytesV1.length);
        //stopWatch.stop();
        
        
        stopWatch.start("fileDataAsBytesV2");
        byte[] fileDataAsBytesV2 = getFileDataAsBytesV2(file);
        PrintUtil.println(fileDataAsBytesV2.length);
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
        
        System.out.println("一共花费时间: " + (System.currentTimeMillis() - start) / 1000.0 + " 秒");
    }
}

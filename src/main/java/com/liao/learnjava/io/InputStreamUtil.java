package com.liao.learnjava.io;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @Project: GlobalRegion
 * @Description
 * @Author: Administrator
 * @Create: 2023/4/22
 **/
public class InputStreamUtil {
    
    @Test
    public void test1() throws IOException {
        File file = new File("doc\\test\\r1.txt");
        byte[] fileDataAsBytes = getFileDataAsBytes(file);
        
        System.out.println(new String(fileDataAsBytes));
    }
    
    // 获得相对路径
    public static String getRelativePath(File childFile, File parentFile) {
        return childFile.getPath().substring(parentFile.getPath().length() + 1);
    }

    /*
    准确地说： https://www.liaoxuefeng.com/wiki/1252599548343744/1298366336073762#0
    byte[] data = new byte[(int)f.length()];
    try(InputStream is =new FileInputStream(f))
    你这两行代码之间，有可能文件会被其他程序修改，导致实际读取的字节与文件长度不一致
    用ByteArrayOutputStream，那个就是用来在内存中开辟缓存的。
    */
    
    /**
     * 将文件内容转为二进制
     *
     * @param f
     * @return
     * @throws IOException
     */
    public static byte[] getFileDataAsBytes(File f) throws IOException {
        
        //byte[] bytes = Files.readAllBytes(Paths.get(f.getPath()));
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        
        byte[] buffer = new byte[1024]; // 缓冲区
        try (InputStream in = new FileInputStream(f)) {
            int n;
            while ((n = in.read(buffer)) != -1) {
                bos.write(buffer, 0, n);
            }
        }
        
        bos.flush();
        byte[] data = bos.toByteArray();
        
        return data;
    }
    
    @NotNull
    public static String readAsString(InputStream input) throws IOException {
        int n;
        StringBuilder sb = new StringBuilder();
        while ((n = input.read()) != -1) {
            sb.append((char) n);
        }
        return sb.toString();
    }
    
    
    public static void demo1() throws IOException {
        String path = "doc\\test\\r2.txt";
        // 定义一个缓冲区
        byte[] buffer = new byte[3];
        List<byte[]> dataBytes = new ArrayList<>();
        try (InputStream input = new FileInputStream(path)) {
            int n;
            int len = 0;
            while ((n = input.read(buffer)) != -1) {    // 读取到缓冲区
                len += n;
                //System.out.println("read " + n + " bytes.");
                dataBytes.add(buffer.clone());
            }
            System.out.println("len长度: " + len);
            System.out.println(dataBytes.size());
            
            //List<byte[]> --> byte[]
            int allLen = dataBytes.stream().mapToInt(e -> e.length).sum();
            System.out.println("allLen = " + allLen);
            
            // 通过缓冲区将，一个文件的内容读取到 另外一个bytes中去， 先循环一遍才能获取到长度，下面再循环一遍进行拷贝
            byte[] bytes = new byte[allLen];
            //System.out.println("bytes长度初始化: " + bytes.length);
            
            int pos = 0;
            for (byte[] bts : dataBytes) {
                System.arraycopy(bts, 0, bytes, pos, bts.length);
                //System.out.println(String.format("拷贝 %s ~ %s", pos, pos + bts.length));
                pos += bts.length;
            }
            
            String s = new String(bytes, 0, len);
            System.out.println("s = " + s + "  字符串长度: " + s.length());
        }
    }
}

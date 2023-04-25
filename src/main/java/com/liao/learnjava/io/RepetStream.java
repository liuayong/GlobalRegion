package com.liao.learnjava.io;

import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @Project: GlobalRegion
 * @Description
 * @Author: Administrator
 * @Create: 2023/4/24
 **/
public class RepetStream {
    /**
     * https://www.jb51.net/article/264405.htm
     *
     * @param args
     * @throws UnsupportedEncodingException
     */
    public static void main(String[] args) throws UnsupportedEncodingException {
        String str = "你好hello";
        int byte_len = str.getBytes().length;
        int len = str.length();
        System.out.println("字节长度为：" + byte_len);
        // 因为在utf-16编码中，该中文字符占了3个字节，英文字符占2个字节。因为在utf-32编码中，所有的字符均占4个字节。
        System.out.println("指定编码: " + str.getBytes("UTF-8").length + ", " + str.getBytes("GBK").length + ", " + str.getBytes("utf-32").length);
        System.out.println("字符长度为：" + len);
        System.out.println("系统默认编码方式：" + System.getProperty("file.encoding"));
    }
    
    /**
     * // String采用一种更灵活的方式进行存储。在String中，一个英文字符占1个字节，而中文字符根据编码的不同所占字节数也不同。在UTF-8编码下，一个中文字符占3个字节；而使用GBK编码时一个中文字符占2个字节
     * //正则表达式，用于匹配中文字符     String regex = "[\u4e00-\u9fa5]";
     *
     * @throws IOException
     */
    @Test
    public void test1() throws IOException {
        
        String text = "测试inputStream内容";
        System.out.println(String.format("java字符串text的长度: %s, 字节数: %s", text.length()));
        InputStream inputStream = new ByteArrayInputStream(text.getBytes());
        byte[] readArray = new byte[inputStream.available()];
        int readCount1 = inputStream.read(readArray);
        System.out.println("读取了" + readCount1 + "个字节");
        
        byte[] readArray2 = new byte[inputStream.available()];
        int readCount2 = inputStream.read(readArray2);
        System.out.println("读取了" + readCount2 + "个字节");
        /**
         *  执行结果是
         *  读取了23个字节
         *  读取了-1个字节
         */
    }
    
    @Test
    public void test2() throws IOException {
        String text = "测试inputStream内容";
        
        // 缓存起来
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(text.getBytes());
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = byteArrayInputStream.read(buffer)) > -1) {
            bao.write(buffer, 0, len);
        }
        bao.flush();
        
        
        ByteArrayOutputStream bao2 = new ByteArrayOutputStream();
        bao2.write(text.getBytes());
        byte[] bytes = bao2.toByteArray();
        String x = new String(bytes);
        System.out.println(x + "," + bao2.toString());
        
        InputStream inputStream = new ByteArrayInputStream(bao.toByteArray());
        byte[] readArray = new byte[inputStream.available()];
        int readCount1 = inputStream.read(readArray);
        System.out.println("读取了" + readCount1 + "个字节");
        
        inputStream = new ByteArrayInputStream(bao.toByteArray());
        byte[] readArray2 = new byte[inputStream.available()];
        int readCount2 = inputStream.read(readArray2);
        System.out.println("读取了" + readCount2 + "个字节");
    }
}

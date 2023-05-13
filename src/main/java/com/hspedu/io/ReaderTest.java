package com.hspedu.io;

import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @Project: GlobalRegion
 * @Description
 * @Author: Administrator
 * @Create: 2023/5/13
 **/
public class ReaderTest {
    
    @Test
    public void test1() throws IOException {
        String pathname = "src/readme.txt";
        File file = new File(pathname);
        System.out.println(file);
        System.out.println(file.exists());
        System.out.println(file.getCanonicalPath());
        
        file = new File("..");
        System.out.println(file);
        System.out.println(file.exists());
        System.out.println(file.getCanonicalPath());
    }
    
    @Test
    public void test2() throws IOException {
        String pathname = "src/readme.txt";
        reader(pathname);
        System.out.println("\n+++++++++++++++++++++++++\n");
        input(pathname);
    }
    
    private void reader(String pathname) throws IOException {
        try (FileReader reader = new FileReader(pathname)) { // StandardCharsets.UTF_8
            char[] buffer = new char[1000];
            for (; ; ) {
                int n;
                if ((n = reader.read(buffer)) == -1) {
                    break;
                }
                System.out.println("read " + n + " chars.");
                System.out.println(buffer);
                System.out.println(buffer.length);
            }
            //reader.close(); // 关闭流
        }
    }
    
    private void input(String pathname) throws IOException {
        try (FileInputStream inputStream = new FileInputStream(pathname)) {
            
            while (true) {
                int n = inputStream.read();
                if (n == -1) {
                    break;
                }
                System.out.print((char) n);
                if (n == 10) {
                    System.out.println((char) n);
                }
                
            }
            inputStream.close();
        }
    }
    
    @Test
    public void test3() throws IOException {
        
        try (Reader reader = new CharArrayReader("Hello".toCharArray(), 1, 2);
             Reader reader2 = new StringReader("你好中国")) {
            char[] buffer = new char[1000];
            int n = reader.read(buffer);
            System.out.println("read1 " + n + " chars.");
            System.out.println(buffer);
            System.out.println(buffer.length);
            int n2 = reader2.read(buffer, n, 10);
            System.out.println("read2 " + n2 + " chars.");
            System.out.println(buffer);
            System.out.println(buffer.length);
            
        }
    }
    
    
    @Test
    public void test4() throws IOException {
        // 持有InputStream:
        InputStream input = new FileInputStream("src/readme.txt");
        // 变换为Reader:
        Reader reader = new InputStreamReader(input, "UTF-8"); // StandardCharsets.UTF_8
        
        int n = 0;
        while ((n = reader.read()) != -1) {
            System.out.print((char) n);
            if (n == 10) {
                System.out.println(  n);
            }
        }
        
        input.close();
        reader.close();
    }
    
}

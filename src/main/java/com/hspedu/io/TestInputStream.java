package com.hspedu.io;

import java.io.*;

/**
 * @Project: GlobalRegion
 * @Description
 * @Author: Administrator
 * @Create: 2023/4/24
 **/
public class TestInputStream {
    
    public void read(InputStream in) throws IOException {
        if (in == null) {
            return;
        }
        int len = 0;
        char ch = 0;
        in.mark(2);
        ch = (char) in.read(); //第一次读取
        System.out.println("ch = " + ch);
        in.reset();//又可以重新读取
        
        ch = (char) in.read();//第二次读取跟第一次读取结果一样。因为只读了一个，没有超过mark设置的整数。所以mark有效
        System.out.println("ch = " + ch);
        
    }
    
    public static void main(String[] args) throws IOException {
        TestInputStream test = new TestInputStream();
        String fileName = "doc\\test\\r1.txt";
        
        //new FileInputStream("doc\\test\\r1.txt")
        InputStream in1 = new FileInputStream(new File(fileName));
        if (!in1.markSupported()) {
            in1 = new BufferedInputStream(in1);
        }
        
        test.read(in1);
        System.out.println("++++++++++++++++++++++++");
        byte[] buf = new byte[100];
        while (in1.read(buf, 0, buf.length) != -1) {
            System.out.println(new String(buf));
        }
        
        System.out.println("Success!");
    }
}

package com.hspedu.io;

import com.byd.tool.PrintUtil;
import com.hspedu.util.StreamUtil;
import com.liao.learnjava.io.CountInputStream;
import com.liao.learnjava.io.InputStreamUtil;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * @Project: GlobalRegion
 * @Description
 * @Author: Administrator
 * @Create: 2023/4/22
 **/
public class FileStreamTest {
    
    @Test
    public void testRelativePath() {
        File parent = new File("D:\\project\\byd\\GlobalRegion\\src\\main\\java\\com\\mexue\\middle");
        File child = new File("D:\\project\\byd\\GlobalRegion\\src\\main\\java\\com\\mexue\\middle\\school\\controller\\PersonController.java");
        String relativePath = InputStreamUtil.getRelativePath(child, parent);
        System.out.println("relativePath = " + relativePath);
    }
    
    @Test
    public void zipTest() throws IOException {
        String path = "D:\\project\\byd\\GlobalRegion\\src\\main\\java\\com\\mexue\\middle";
        toZip(new File(path));
    }
    
    public static void toZip(File source) throws IOException {
        File target = new File("D:\\Java\\demo2.zip");
        try (ZipOutputStream out = new ZipOutputStream(new FileOutputStream(target))) {
            putFileToZip(source, source, out);
        }
    }
    
    private static void putFileToZip(File file, File root, ZipOutputStream out) throws IOException {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                putFileToZip(f, root, out);
            }
        } else {
            ZipEntry zipEntry = new ZipEntry(InputStreamUtil.getRelativePath(file, root));
            out.putNextEntry(zipEntry);
            out.write(InputStreamUtil.getFileDataAsBytes(file));
        }
    }
    
    
    @Test
    public void testReadZip() throws IOException {
        try (ZipInputStream zipInput = new ZipInputStream(new FileInputStream("w2.zip"))) {
            ZipEntry zipEntry = null;
            while ((zipEntry = zipInput.getNextEntry()) != null) {
                String name = zipEntry.getName();
                System.out.println("name = " + name + ", " + zipEntry.isDirectory());
            }
        }
        
    }
    
    @Test
    public void testWriteZip() throws IOException {
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream("w2.zip"));
        File[] files = {new File("doc\\test\\r1.txt"), new File("doc\\test\\r2.txt"), new File("人才简历整理.php")};
        for (File f : files) {
            //  每条zip文件的文件名
            out.putNextEntry(new ZipEntry(f.toPath().toString()));
            System.out.println(f.toPath() + ", " + f.getName() + ", " + f.getPath());
            out.write(Files.readAllBytes(f.toPath()));
            // 写进一个文件关闭一个文件
            out.closeEntry();
        }
        out.close();
    }
    
    
    @Test
    public void test7() throws IOException {
        //ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream("xxx.zip"));
        //ZipEntry nextEntry = zipInputStream.getNextEntry();
        File file = new File("doc\\test\\r1.txt");
        System.out.println(file.toPath() + "," + Paths.get(file.getPath()) + ", " + (Paths.get(file.getPath()).equals(file.toPath())));
        
        byte[] bytes = Files.readAllBytes(file.toPath());
        System.out.println(new String(bytes));
        byte[] bytes2 = Files.readAllBytes(Paths.get("doc\\test\\r2.txt"));
        System.out.println(new String(bytes2));
        
    }
    
    @Test
    public void test6() throws IOException {
        byte[] data = "hello, world!!".getBytes("UTF-8");
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
        InputStream input = new FileInputStream("doc\\test\\r1.txt");
        try (CountInputStream in = new CountInputStream(input)) {
            
            System.out.println("init available = " + in.available());
            
            //String string = InputStreamUtil.readAsString(in);
            //System.out.println("Total read " + in.getBytesRead() + " bytes");
            //in.reset();
            
            int n;
            //while ((n = in.read()) != -1) {
            //    System.out.print ((char) n);
            //}
            byte[] bytes = new byte[data.length + 10];
            //while ((n = in.read(bytes, 0, 3)) != -1) {
            //    System.out.println("n = " + n + ", available = " + in.available());
            //}
            while ((n = in.read(bytes)) != -1) {
                System.out.println("n = " + n + ", available = " + in.available());
            }
            
            System.out.println(" Total read " + in.getBytesRead() + " bytes");
        }
    }
    
    public static void main(String[] args) throws IOException {
        String s;
        try (InputStream input = new FileInputStream("doc\\test\\r1.txt");
             InputStream input2 = new ByteArrayInputStream("world".getBytes(StandardCharsets.UTF_8))
        ) {
            s = InputStreamUtil.readAsString(input);
            System.out.println(s);
            System.out.println(InputStreamUtil.readAsString(input2));
        }
    }
    
    
    @Test
    public void test5() throws IOException {
        byte[] data = {72, 101, 108, 108, 111, 33};
        try (InputStream input = new ByteArrayInputStream("helllo".getBytes(StandardCharsets.UTF_8))) {
            int n;
            while ((n = input.read()) != -1) {
                System.out.print((char) n + " ");
            }
        }
        
    }
    
    
    @Test
    public void test4() {
        String[] words = {"Hello", "World"};
        List<String[]> collect = Arrays.stream(words)
                .map(word -> word.split(""))
                //.distinct()
                .collect(Collectors.toList());
        for (String[] strings : collect) {
            System.out.println(Arrays.toString(strings));
            PrintUtil.println(strings);
        }
        System.out.println("++++++++++++++++++++++++++++++++++++");
        
        
        List<String> collect1 = Arrays.asList(words).stream()
                .flatMap(word -> Arrays.stream(word.split("")))
                .collect(Collectors.toList());
        System.out.println(Arrays.toString(collect1.toArray()));
        
        
    }
    
    @Test
    public void test3() throws IOException {
        InputStreamUtil.demo1();
        
    }
    
    
    @Test
    public void test2() throws IOException {
        String path = "D:\\project\\byd\\GlobalRegion\\doc\\test\\r1.txt";
        String path2 = "doc\\test\\r2.txt";
        InputStream input = null;
        Reader reader = null;
        
        try {
            // 创建一个FileInputStream对象:
            input = new FileInputStream(path2);
            readFile(input);
            
            reader = new FileReader(path2);
            readFile(reader);
        } finally {
            if (input != null) input.close();
            if (reader != null) reader.close();
            System.out.println(reader);
            System.out.println(input);
        }
    }
    
    
    private void readFile(Reader reader) throws IOException {
        for (; ; ) {
            int read = reader.read();
            if (read == -1) {
                break;
            }
            System.out.print((char) read + " ");
        }
        reader.close();
        System.out.println("\n+++++++ Reader: 中文不乱码 ++++++++++++");
        
    }
    
    private void readFile(InputStream input) throws IOException {
        
        for (; ; ) {
            int read = input.read();
            if (read == -1) {
                break;
            }
            System.out.print((char) read + " ");
        }
        System.out.println("\n+++++++++ InputStream ++++++++++");
        //System.out.println(input.available());
        
    }
    
    
    @Test
    public void test1() throws IOException {
        byte[] data = {72, 101, 108, 108, 111, 33};
        try (InputStream input = new ByteArrayInputStream(data)) {
            System.out.println("available = " + input.available());
            int n;
            while ((n = input.read()) != -1) {
                System.out.println((char) n);
            }
            System.out.println("available = " + input.available());
            
            input.reset();
            System.out.println("available = " + input.available());
            input.skip(2);
            System.out.println("available = " + input.available());
            
            byte[] bytes = new byte[9];
            input.read(bytes);
            PrintUtil.println(bytes);
            String str = new String(bytes);
            PrintUtil.println(str + ", " + str.length());
            
            for (byte aByte : bytes) {
                System.out.println((char) aByte);
            }
        }
    }
}

package com.hspedu.io;

import org.junit.Test;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

/**
 * @Project: GlobalRegion
 * @Description
 * @Author: Administrator
 * @Create: 2023/4/27
 **/
public class ClassPathTest {
    
    
    /**
     * https://blog.csdn.net/magi1201/article/details/18731581
     */
    @Test
    public void test1() {
        URL resource = ClassPathTest.class.getResource("/");
        System.out.println(resource);
        String path = ClassPathTest.class.getResource("/").toString();
        System.out.println("path = " + path);
        
        System.out.println(Thread.currentThread().getContextClassLoader());
        System.out.println(Thread.currentThread().getClass());
        System.out.println(Thread.currentThread().getClass().getClassLoader());
        System.out.println(this.getClass().getClassLoader() == ClassPathTest.class.getClassLoader());
        System.out.println(this.getClass().getClassLoader().getResource(""));
        String path2 = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        System.out.println("path2 = " + path2);
    }
    
    /**
     * 功能说明：测试工程路径、classpath、类的完整路径
     * 修改说明：
     * https://blog.csdn.net/zlbdmm/article/details/105769143
     * System.getProperty(“user.dir”);是获取的当前工程路径，如果是在你的类中是处理源代码可以用这个路径，
     * 如果是解析配置文件，比如.properties文件，一定要用classpath的相对路径。
     *
     * @date 2020年4月26日 下午2:42:07
     */
    @Test
    public void testPath() {
        try {
            String projectPath = System.getProperty("user.dir");                    //获取当前eclipse工程路径
            String classPath = this.getClass().getResource("/").toString();            //获取当前classPath
            //			String classPath = this.getClass().getClassLoader().getResource("");	//获取当前classPath等同上一行代码
            String classFullPath = this.getClass().getResource("").toString();        //获取当前类基于classPath的完整路径
            
            System.out.println(projectPath);
            System.out.println(classPath);
            System.out.println(classFullPath);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    @Test
    public void test2() throws URISyntaxException, IOException {
        //方式一
        String path = "jackson/book.json";
        path = "schema.sql";
        path = "httpAdapter.properties";
        InputStream in = this.getClass().getClassLoader().getResourceAsStream(path);
        assert in != null;
        // InputStream --> String
        
        byte[] bytes = new byte[2048];
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        int n;
        while ((n = in.read(bytes)) != -1) {
            System.out.println("n = " + n);
            bos.write(bytes, 0, n);
        }
        System.out.println(bos.toString());
        String s = new String(bos.toByteArray());
        System.out.println((bos.toString().equals(s)));
        
        System.out.println("+++++++++++++++++++++++++++");
        //方式二
        URL url = this.getClass().getClassLoader().getResource(path);
        System.out.println(url);
        System.out.println(url.toURI());
        
        File file = new File(url.toURI());
        System.out.println(file.exists());
        
        URL url2 = this.getClass().getResource("/" + path);
        System.out.println(url2);
        System.out.println((url2 == url) + ", " + url.equals(url2));
        File file2 = new File(url2.toURI());
        System.out.println(file2.exists() + ", " + file2.equals(file));
        
    }
    
    @Test
    public void test3() {
        String name = "dcs/httpAdapter.properties";
        try (InputStream input = getClass().getResourceAsStream("/" + name);
             InputStream input2 = getClass().getClassLoader().getResourceAsStream(name)
        ) {
            System.out.println("input = " + input2 + ", " + (input == input2) + ", " + (input.equals(input2)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void getDcsResource() {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("dcs/httpAdapter.properties");
        System.out.println("in = " + in);
        try {
            Properties pro = new Properties();
            pro.load(in);
            String dcsUrl = pro.getProperty("HTTP_DCS_URL");
            System.out.println("dcsUrl = " + dcsUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    @Test
    public void getProperties() throws IOException {
        
        File file = new File("./user.properties");
        System.out.println(file.exists());
        System.out.println(file.getAbsolutePath());
        System.out.println(file.getPath());
        System.out.println(file.getCanonicalPath());
        
        try (InputStream in = this.getClass().getClassLoader().getResourceAsStream("default.properties");
             InputStream userIn = new FileInputStream("user.properties")
        ) {
            Properties properties = new Properties();
            properties.load(in);
            System.out.println("userIn = " + userIn);
            properties.load(userIn);
            
            Set<Object> keySet = properties.keySet();
            Iterator<Object> iterator = keySet.iterator();
            while (iterator.hasNext()) {
                String key = (String) iterator.next();
                String value = properties.getProperty(key);
                System.out.println(String.format("key:%s, value:%s", key, value));
            }
            String passWord = properties.getProperty("passWord");
            System.out.println("|" + passWord + "|");
            
        }
    }
    
    @Test
    public void test4() {
        System.out.println(ZipTest.class.getResource(""));
        System.out.println(ZipTest.class.getResource("/"));
        System.out.println(ZipTest.class.getResource("/default.properties"));
        System.out.println(ZipTest.class.getResource("default.properties"));
        System.out.println(ZipTest.class.getResource("./default.properties"));
    }
}

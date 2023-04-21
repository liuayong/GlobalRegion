package com.hspedu.test;

import com.byd.tool.PrintUtil;
import com.hspedu.util.FileUtil;
import com.hspedu.util.MultipartFileUtil;
import org.junit.Test;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

public class MultipartFileUtilTest {

    @Test
    public void test1() {
        File file = new File(".");
        System.out.println(file.isDirectory());
        System.out.println(file.isFile());
        System.out.println(file.getAbsolutePath());

        List<String> strings = FileUtil.allFiles(file);

        PrintUtil.println(strings);
    }

    @Test
    public void test2() throws Exception {
        MultipartFile multipartFile1 = MultipartFileUtil.toMultipartFile1();

        System.out.println(multipartFile1.getName());
        System.out.println(multipartFile1.getContentType());
        System.out.println(new String(multipartFile1.getBytes()));
        System.out.println(multipartFile1);
        MultipartFile multipartFile2 = MultipartFileUtil.toMultipartFile2();
        System.out.println(new String(multipartFile2.getBytes()));


    }

}

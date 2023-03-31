package com.hspedu.util;

import com.byd.tool.PrintUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Project: GlobalRegion
 * @Description
 * @Author: Administrator
 * @Create: 2023/3/4
 **/
@Slf4j
public class FileUtil {


    /**
     * 递归列出某个文件夹下的文件列表
     *
     * @param file
     * @return
     */
    public static List<String> allFiles(File file) {
        File[] files = file.listFiles();
        List<String> fileList = new ArrayList<>();
        assert files != null;
        for (File itFile : files) {
            if (itFile.isDirectory()) {
                fileList.addAll(allFiles(itFile));
            } else {
                fileList.add(itFile.getAbsolutePath());
            }
        }
        return fileList;
    }

    public static void main(String[] args) {
        String path = "D:\\project\\byd\\GlobalRegion\\src\\main\\java\\com\\hspedu\\classload_";
        process(path);
    }

    public static void process(String pathStr) {
        File file = new File(pathStr);
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                process(f.getAbsolutePath());
            }
        } else if (file.isFile()) {
            //是文件则判断是否是.java文件
            if (file.getName().matches(".*\\.java$")) {       // matches(".*\\.java$") || endsWith(".xls")
                Map<String, Integer> parseMap = parse(file);
                PrintUtil.println(parseMap);
            }
        } else {
            log.error("file = {}", file);
        }
    }

    private static Map<String, Integer> parse(File file) {
        int codeLines = 0;
        int commentLines = 0;
        int whiteLines = 0;
        BufferedReader br = null;
        Map<String, Integer> map = new HashMap<>();
        try {
            br = new BufferedReader(new FileReader(file));
            String line = "";
            while ((line = br.readLine()) != null) {
                line = line.trim();//清空每行首尾的空格
                if (line.matches("^[\\s&&[^\\n]]*$")) {//注意不是以\n结尾, 因为在br.readLine()会去掉\n
                    whiteLines++;
                } else if (line.startsWith("/*") || line.startsWith("*") || line.endsWith("*/")) {
                    commentLines++;
                } else if (line.startsWith("//") || line.contains("//")) {
                    commentLines++;
                } else {
                    if (line.startsWith("import") || line.startsWith("package")) {//导包不算
                        continue;
                    }
                    codeLines++;
                }
            }
            System.out.println("文件: " + file.getName());
            map.put("codeLines", codeLines);
            map.put("commentLines", commentLines);
            map.put("whiteLines", whiteLines);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != br) {
                try {
                    br.close();
                    br = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return map;
    }

    /**
     * 返回文件的URL地址。
     *
     * @param file 文件
     * @return 文件对应的的URL地址
     * @throws MalformedURLException
     * @since 1.0
     * @deprecated 在实现的时候没有注意到File类本身带一个toURL方法将文件路径转换为URL。
     * 请使用File.toURL方法。
     */
    public static URL getURL(File file) throws MalformedURLException {
        String fileURL = "file:/" + file.getAbsolutePath();
        URL url = new URL(fileURL);
        return url;
    }

    public static byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }
}

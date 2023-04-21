package com.hspedu.util;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;
import java.nio.file.Files;


/**
 * https://blog.csdn.net/sdut406/article/details/85647982
 */
public class MultipartFileUtil {


    public static File toFile1() {
        return null;
    }

    /**
     * (1)：使用org.springframework.mock.web.MockMultipartFile 需要导入spring-test.jar
     *
     * @return
     */
    public static MultipartFile toMultipartFile1() throws Exception {
        String filePath = "F:\\test.txt";
        filePath = "人才简历整理.php";

        File file = new File(filePath);
        FileInputStream fileInputStream = new FileInputStream(file);

        // MockMultipartFile(String name, @Nullable String originalFilename, @Nullable String contentType, InputStream contentStream)
        // 其中originalFilename,String contentType 旧名字，类型  可为空
        // ContentType.APPLICATION_OCTET_STREAM.toString() 需要使用HttpClient的包
        // org.apache.http.entity.ContentType.APPLICATION_OCTET_STREAM.toString()

        MultipartFile multipartFile = new MockMultipartFile("copy" + file.getName(), file.getName(), "application/octet-stream", fileInputStream);
        System.out.println(multipartFile.getName() + ", " + file.getName() + ", " + multipartFile.getOriginalFilename()); // 输出copytest.txt

        return multipartFile;
    }

    /**
     * (2)：使用CommonsMultipartFile
     *
     * @return
     * @throws IOException
     */
    public static MultipartFile toMultipartFile2() throws IOException {
        String filePath = "人才简历整理.php";

        File file = new File(filePath);

        FileItem fileItem = new DiskFileItem("copyfile.txt", Files.probeContentType(file.toPath()),
                false, file.getName(), (int) file.length(), file.getParentFile());
        byte[] buffer = new byte[4096];
        int n;
        try (InputStream inputStream = new FileInputStream(file); OutputStream os = fileItem.getOutputStream()) {
            while ((n = inputStream.read(buffer, 0, 4096)) != -1) {
                os.write(buffer, 0, n);
            }
            //也可以用IOUtils.copy(inputStream,os);
            MultipartFile multipartFile = new CommonsMultipartFile(fileItem);
            return multipartFile;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}

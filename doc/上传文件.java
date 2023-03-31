
@RequestParam(value = "file", required = true) MultipartFile multipartFile
@RequestPart("file") MultipartFile 
public static final String MULTIPART_FORM_DATA_VALUE = "multipart/form-data";
@RequestMapping(path = "/employee", method = POST, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
https://www.jb51.net/article/198882.htm  Feign之Multipartfile文件传输填坑
https://blog.csdn.net/u013168084/article/details/107911335

https://www.baeldung.com/sprint-boot-multipart-requests
@RequestMapping(path = "/requestparam/employee", method = POST, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
public ResponseEntity<Object> saveEmployee(@RequestParam String name, @RequestPart MultipartFile document) {
    Employee employee = new Employee(name, document);
    employeeService.save(employee);
    return ResponseEntity.ok().build();
}
<!-- https://mvnrepository.com/artifact/commons-fileupload/commons-fileupload -->
<dependency>
	<groupId>commons-fileupload</groupId>
	<artifactId>commons-fileupload</artifactId>
	<version>1.4</version>
</dependency>

https://juejin.cn/post/7168369943421911047
https://www.cnblogs.com/biehongli/p/13964515.html
https://www.cnblogs.com/biehongli/p/13964515.html
https://www.52interview.com/book/15/171



在 Java 中使用 InputStream 读取文件
https://www.techiedelight.com/zh/how-to-read-file-using-inputstream-java/
https://blog.csdn.net/wanwei1987/article/details/80884216
https://www.cnblogs.com/ddwarehouse/p/10127729.html

上传文件是否存在
https://blog.csdn.net/weixin_45824805/article/details/111601351










package com.littlefox.area.controller;

import com.byd.tool.PrintUtil;
import com.littlefox.area.vo.AreaVo;
import com.mexue.middle.school.common.Result;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;

@RestController
@RequestMapping("/upload")
@Slf4j
public class UploadController {

    @Resource
    private HttpServletRequest request;

    @ApiOperation(value = "导入文本数据", notes = "批量导入学校")
    // @RequestMapping(value = "/form", method = RequestMethod.POST, consumes = "multipart/form-data")
    @PostMapping("/imports")
    Result imports(@RequestParam(value = "file", required = false) MultipartFile multipartFile, AreaVo areaVo) throws IOException {
        InputStream inputStream = multipartFile.getInputStream();
        String contents = "";
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if (multipartFile != null && validateFile(multipartFile, map)) {
                // 设置文件名称
                map.put("nameParam", multipartFile.getOriginalFilename());
                // 设置文件名称
                map.put("fileName", multipartFile.getName());
                // 设置文件类型
                map.put("contentType", multipartFile.getContentType());
                // 设置文件大小
                map.put("fileSize", multipartFile.getSize());
                // 创建文件名称
                String fileName = UUID.randomUUID() + "."
                        + multipartFile.getContentType().substring(multipartFile.getContentType().lastIndexOf("/") + 1);
                // 获取到文件的路径信息
                RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
                ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
                String filePath = servletRequestAttributes.getRequest().getServletContext().getRealPath("/") + fileName;
                // 打印保存路径
                System.out.println(filePath);
                // 保存文件的路径信息
                map.put("filePath", filePath);
                // 创建文件
                File saveFile = new File(filePath);
                // 文件保存
                multipartFile.transferTo(saveFile);
                // 返回信息


            }
            contents = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintUtil.println(request.getParameterMap());
        log.info("areaVo = {}, map = {}, contents = {}", areaVo, map, contents);
        return Result.OK(ObjectUtils.isEmpty(contents) ? areaVo : contents);
    }

    /**
     * 上传文件是否校验通过
     *
     * @param file
     * @param map
     * @return
     */
    private boolean validateFile(MultipartFile file, Map<String, Object> map) {
        List<String> errList = new ArrayList<>();
        if (!map.containsKey("errMsg")) {
            map.put("errMsg", errList);
        }
        if (file.isEmpty()) {
            errList.add("上传的文件不能为空！请重新上传");
        }
        if (file.getSize() <= 0) {
            errList.add("上传的文件大小需要大于0kb");
        }
        return errList.isEmpty();
    }
}

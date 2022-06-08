package com.hspedu.service;

import com.alibaba.excel.metadata.Sheet;

import java.io.File;
import java.util.List;

public class ExcleService {
    
    public static void main(String[] args) {
        String filePath = "C:\\Users\\jun\\Desktop\\transfer\\11.xls";
        
        File file = new File(filePath);
        System.out.println(file.exists());
        
        
        Sheet sheet = new Sheet(1, 1);
        List<Object> objects = ExcelUtil.readLessThan1000Row(filePath, sheet);
        
    }
}

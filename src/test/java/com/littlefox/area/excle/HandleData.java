package com.littlefox.area.excle;

import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.littlefox.area.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Test
 *
 * @author rockychen
 */
@RunWith(SpringRunner.class)
//@SpringBootTest
@SpringBootTest(classes = Application.class)
public class HandleData {
    
    /**
     * 测试 将数据写入excel
     */
    @Test
    public void testWrite() {
        // 模拟写入的数据,实际生产中应该是数据库中查询出来的
        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 1; i < 100; i++) {
            Map map = new HashMap();
            map.put("id", i++);
            map.put("name", "李东平-" + i);
            map.put("age", "18");
            map.put("weChat", "851298348");
            list.add(map);
        }
        ExcelWriter writer = ExcelUtil.getWriter("D:\\test\\t1.xls");
        ExcelWriter write = writer.write(list);
        write.flush();
        System.out.println("写入数据完成");
    }
    
}

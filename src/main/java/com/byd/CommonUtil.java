package com.byd;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class CommonUtil {
    
    public static final String VER1 = "Version1";
    public static final String VER2 = "Version2";
    public static final String VER3 = "Version3";
    
    
    
    public static LocalDate toLocalDate(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        
        // atZone()方法返回在指定时区从此Instant生成的ZonedDateTime。
        LocalDate localDate = instant.atZone(zoneId).toLocalDate();
        return localDate;
    }
}

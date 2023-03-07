package com.byd;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class DateTest {
    
    
    @Test
    public void test1() {
        String invoStr = "{\"clientId\":\"800\",\"dims\":[\"KUNNR\",\"MATNR\"],\"disType\":\"M\",\"divNo\":\"D15\"," +
                "\"factNo\":\"A155\",\"genFactNo\":\"0035\",\"language\":\"1\",\"orders\":[],\"pageNum\":1,\"pageSize\":50,\"startPlanTime1\":\"2020-03-01\",\"startPlanTime2\":\"2020-01-01\",\"timeVersionId1\":1374321620721664,\"timeVersionId2\":1374266202120192,\"versionNo1\":\"20230301\",\"versionNo2\":\"20230228\",\"volatilityType\":1}";
        String moduleVersion = "{\"bbType\":\"MONTH_ROLL\",\"createDate\":1677570810000," +
                "\"createUser\":1366890761322496,\"deleted\":\"N\",\"divNo\":\"D15\",\"factNo\":\"A155\",\"genFactNo\":\"0035\",\"id\":1374266008330240," +
                "\"modifyDate\":1677570826000,\"modifyUser\":1366890761322496,\"ttDay\":0,\"ttMonth\":5,\"ttWeek\":0,\"useRmk\":\"1\"," +
                "\"versionNo\":\"20230228001\"}";
        
        
    }
    
    
}

package com.littlefox.area.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

/**
 * https://www.jianshu.com/p/55e6e0943d79
 * https://cloud.tencent.com/developer/article/1868921
 */
@Data
//@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class GetCarResponse {
    
    /**
     * 上家系统订单号，唯一、不可重复、不可空
     */
    @JsonProperty(value = "order_card")
    
    private String orderCard;
    
    
    /**
     * 卡号(唯一，不可重复)
     */
    private String cardId;
    
    @JsonProperty(value = "my_name")
    private String testName;
    
    
    @JsonProperty(value = "user_Name")
    private String name;
}

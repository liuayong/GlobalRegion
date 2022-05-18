package com.littlefox.area.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 传入参数的时候(下换线转为驼峰)
 * https://www.jianshu.com/p/55e6e0943d79
 * https://cloud.tencent.com/developer/article/1868921
 * https://www.cnblogs.com/newAndHui/p/14767675.html
 */
@Data
public class GetCarRequest implements Serializable {
    
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
    private String userName;
}

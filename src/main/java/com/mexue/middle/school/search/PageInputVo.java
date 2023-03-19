package com.mexue.middle.school.search;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PageInputVo {
    @ApiModelProperty("页码, 第几页")
    private Integer pageNum = 1;

    @ApiModelProperty("每页显示记录数")
    private Integer pageSize = 20;

    public PageInputVo(){}
    public PageInputVo(Integer pageNum, Integer pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }
}

package com.hspedu.date;

import com.mexue.middle.school.util.DateUtil;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class DatePeriodVo {
    @ApiModelProperty("期间(周/月")
    private Integer period;
    
    
    @ApiModelProperty("需求日期开始时间")
    private Date periodstart;
    
    @ApiModelProperty("需求日期结束时间")
    private Date periodEnd;
    
    
    @ApiModelProperty("日期排序")
    private int idx;
    
    public DatePeriodVo(Integer period, Date periodstart, int idx) {
        this.period = period;
        this.periodstart = periodstart;
        this.idx = idx;
    }
    
    public DatePeriodVo(Integer period, Date periodstart, Date periodEnd, int idx) {
        this.period = period;
        this.periodstart = periodstart;
        this.periodEnd = periodEnd;
        this.idx = idx;
    }
    
    @Override
    public String toString() {
        return "DatePeriodVo{" +
                "period=" + period +
                ", periodstart=" + DateUtil.dateToStrSS(periodstart)  +
                ", periodEnd=" + DateUtil.dateToStrSS(periodEnd) +
                ", idx=" + idx +
                '}';
    }
}
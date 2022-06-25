package com.mexue.middle.school.search;

import java.io.Serializable;

public class BaseSearch implements Serializable {
    private Integer pageNum = 1;
    private Integer pageSize = 20;
    private int count;

    private int offset = 0;


    private String orderStr; // 直接写order命令
    private Integer orderKey; // 需要动态写order命令
    private Integer orderValue; // 需要动态写order命令

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getOffset() {
        return (this.pageNum - 1) * this.pageSize;
    }

    public boolean hasPrevious() {
        return this.pageNum > 1;
    }

    public boolean hasNextPage() {
        return this.pageNum * this.getPageSize() < this.getCount();
    }

    public int getPageCount() {
        return this.getCount() == 0 ? 0 : (int)Math.ceil((double)this.count / (double)this.getPageSize());
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(Integer pageSize) {
        //if (pageSize != null && pageSize >= 1) {
            this.pageSize = pageSize;
        /*} else {
            this.pageSize = 15;
        }*/

    }

    public void setPageNum(Integer pageNum) {
        if (pageNum != null && pageNum >= 1) {
            this.pageNum = pageNum;
        } else {
            this.pageNum = 1;
        }

    }

    public Integer getCurPage() {
        return this.pageNum;
    }

    public Integer getLimit() {
        return this.pageNum;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public String getOrderStr() {
        return orderStr;
    }

    public void setOrderStr(String orderStr) {
        this.orderStr = orderStr;
    }

    public Integer getOrderKey() {
        return orderKey;
    }

    public void setOrderKey(Integer orderKey) {
        this.orderKey = orderKey;
    }

    public Integer getOrderValue() {
        return orderValue;
    }

    public void setOrderValue(Integer orderValue) {
        this.orderValue = orderValue;
    }
}

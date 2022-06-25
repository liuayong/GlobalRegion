package com.mexue.middle.school.common;

import org.apache.poi.ss.formula.functions.T;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageResult<E> {
    //页码
    private Integer pageNum = 1;
    //每一页显示个数
    private Integer pageSize = 15;
    //总记录条数
    private int count = 0;
    
    private List<E> items;
    
    /**
     * 分页携带扩展信息
     */
    private Map<String, Object> extend;
    
    
    public PageResult(Integer pageNum, Integer pageSize, Integer count) {
        if (pageNum == null || pageNum.intValue() < 0) {
            this.pageNum = 1;
        } else {
            this.pageNum = pageNum;
        }
        
        if (pageSize == null || pageSize.intValue() < 0) {
            this.pageSize = 15;
        } else {
            this.pageSize = pageSize;
        }
        if (count == null || count.intValue() < 0) {
            this.count = 0;
        } else {
            this.count = count;
        }
    }
    
    public PageResult(long totalCount, List<E> dataList) {
        this.count = (int) totalCount;
        this.items = dataList;
    }
    
    public int getCount() {
        return count;
    }
    
    public void setCount(int count) {
        this.count = count;
    }
    
    public Integer getPageNum() {
        return pageNum;
    }
    
    public Integer getPageSize() {
        return pageSize;
    }
    
    public void setPageSize(Integer pageSize) {
        if (pageSize == null || pageSize.intValue() < 1) {
            this.pageSize = 15;
        } else {
            this.pageSize = pageSize;
        }
    }
    
    public void setPageNum(Integer pageNum) {
        if (pageNum == null || pageNum.intValue() < 1) {
            this.pageNum = pageNum;
        } else {
            this.pageNum = pageNum;
        }
    }
    
    public List<E> getItems() {
        return items;
    }
    
    public void setItems(List<E> items) {
        this.items = items;
    }
    
    public int getPageCount() {
        return getCount() == 0 ? 0 : (int) Math.ceil((double) count / (double) getPageSize());
    }
    
    public Map<String, Object> getExtend() {
        return extend;
    }
    
    public void setExtend(Map<String, Object> extend) {
        this.extend = extend;
    }
    
    public void putExtend(String key, Object data) {
        if (null == extend) {
            extend = new HashMap<>();
        }
        extend.put(key, data);
    }
}

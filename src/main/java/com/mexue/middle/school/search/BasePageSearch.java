package com.mexue.middle.school.search;

import java.io.Serializable;
import java.util.LinkedHashMap;

public class BasePageSearch implements Serializable {
    private Integer pageNum = 1;
    private Integer pageSize = 20;
    private Integer offset = 0;
    private Integer topNum = 0;//前几条
    private String orderParams;//后台追加的排序参数
    private String orderParamsForm;// 页面传参的排序参数 [Created by 2019-07-08 10:57 DJ]
    private LinkedHashMap<String, Direction> orderParamsMap;
    private String likeKeyword;//关键词查询，默认过滤所有的字符串类型模糊查询OR关系
    
    
    public int getCurPage() {
        return this.pageNum;
    }
    
    public int getPageNumber() {
        return this.pageNum;
    }
    
    
    public Integer getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(Integer pageSize) {
        if (pageSize != null && pageSize >= 1) {
            this.pageSize = pageSize;
        } else {
            this.pageSize = 20;
        }
    }

    public void setPageNum(Integer pageNum) {
        if (pageNum != null && pageNum >= 1) {
            this.pageNum = pageNum;
        } else {
            this.pageNum = 1;
        }
    }

    public Integer getPageNum() {
        return this.pageNum;
    }

    public int getOffset() {
        this.offset = (this.pageNum - 1) * this.pageSize;
        return this.offset ;
    }

    public void addOrder(String column, Direction direction) {
        if (null == orderParamsMap) {
            orderParamsMap = new LinkedHashMap<>();
        }
        orderParamsMap.put(column, direction);
    }

    public void addOrder(String column) {
        if (null == orderParamsMap) {
            orderParamsMap = new LinkedHashMap<>();
        }
        orderParamsMap.put(column, null);
    }

    private void reloadOrder() {
        //StringBuffer orderParamsBuffer = new StringBuffer(EntityUtil.isEmpty(orderParamsForm) ? "" : orderParamsForm);
        //if (null != orderParamsMap && orderParamsMap.size() > 0) {
        //    for (String oneColumn : orderParamsMap.keySet()) {
        //        if (orderParamsBuffer.length() > 0) {
        //            orderParamsBuffer.append(",");
        //        }
        //        orderParamsBuffer.append(oneColumn);
        //        orderParamsBuffer.append(" ");
        //        orderParamsBuffer.append(null == orderParamsMap.get(oneColumn) ? Direction.ASC.getCode() : orderParamsMap.get(oneColumn).getCode());
        //        orderParamsBuffer.append(" ");
        //    }
        //}
        //if (orderParamsBuffer.length()>0){
        //    this.orderParams = orderParamsBuffer.toString();
        //}
    }

    public String getOrderParams() {
        reloadOrder();
        return this.orderParams;
    }

    public String getOrderParamsForm() {
        return orderParamsForm;
    }

    public void setOrderParamsForm(String orderParamsForm) {
        this.orderParamsForm = orderParamsForm;
    }

    public String getLikeKeyword() {
        return likeKeyword;
    }

    public void setLikeKeyword(String likeKeyword) {
        this.likeKeyword = likeKeyword;
    }

    public Integer getTopNum() {
        return topNum;
    }

    public void setTopNum(Integer topNum) {
        this.topNum = topNum;
    }
}

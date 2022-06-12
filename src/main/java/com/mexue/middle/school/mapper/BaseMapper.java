package com.mexue.middle.school.mapper;

public interface BaseMapper<T> {
    int deleteByPrimaryKey(Integer id);
    
    int insert(T record);
    
    int insertSelective(T record);
    
    T selectByPrimaryKey(Integer id);
    
    //T selectByRecord(T record);
    
    int updateByPrimaryKeySelective(T record);
    
    int updateByPrimaryKey(T record);
}
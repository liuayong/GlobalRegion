package com.mexue.middle.school.service.impl;

import com.mexue.middle.school.mapper.BaseMapper;
import com.mexue.middle.school.service.BaseService;

import javax.annotation.Resource;

public class BaseServiceImpl<T> implements BaseService<T> {
    //@Autowired
    @Resource
    protected BaseMapper<T> baseMapper;
    
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return baseMapper.deleteByPrimaryKey(id);
    }
    
    @Override
    public int insert(T record) {
        return baseMapper.insert(record);
    }
    
    @Override
    public int insertSelective(T record) {
        return baseMapper.insertSelective(record);
    }
    
    @Override
    public T selectByPrimaryKey(Integer id) {
        //System.out.println("baseServiceImpl baseMapper = " + baseMapper);
        return baseMapper.selectByPrimaryKey(id);
    }
    
    @Override
    public int updateByPrimaryKeySelective(T record) {
        return baseMapper.updateByPrimaryKeySelective(record);
    }
    
    @Override
    public int updateByPrimaryKey(T record) {
        return baseMapper.updateByPrimaryKey(record);
    }
    
    
}

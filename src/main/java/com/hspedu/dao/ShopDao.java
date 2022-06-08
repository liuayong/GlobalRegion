package com.hspedu.dao;


import com.hspedu.model.Shop;

public interface ShopDao  extends BaseDao<Shop>{
    void add(Shop shop);
    
    void delete(Shop shop);
    
    void update(Shop shop);
    
    Shop select(Shop shop);
}

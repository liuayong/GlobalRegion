package com.hspedu.dao.impl;

import com.hspedu.dao.UserDao;
import com.hspedu.helper.JdbcDaoHelper;
import com.hspedu.model.User;

import java.lang.reflect.ParameterizedType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImp extends BaseDaoImp<User> implements UserDao {
    
    private Class<?> EntityClass;
    
    private String sql;
    
    private PreparedStatement statement;
    
    private ResultSet rs;
    
    private List<User> list;
    
    public UserDaoImp() {
        
        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        EntityClass = (Class<?>) type.getActualTypeArguments()[0];
    }
    
    
    @Override
    public List<User> findAll() {
        // TODO Auto-generated method stub
        StringBuffer b = new StringBuffer();
        list = new ArrayList<User>();
        sql = b.append("select * from " + EntityClass.getSimpleName())
                .toString();
        try {
            statement = JdbcDaoHelper.getPreparedStatement(sql);
            rs = statement.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setUsername(rs.getString("username"));
                user.setGrade(rs.getInt("grade"));
                list.add(user);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }
    
}

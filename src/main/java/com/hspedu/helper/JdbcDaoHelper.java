package com.hspedu.helper;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcDaoHelper {
    
    private static final String USER = "root";
    
    private static final String PASSWORD = "ayongAli.,123";
    
    private static final String URL = "jdbc:mysql://101.132.79.49:3306/middle_school";
    
    private static Connection con;
    
    // 获取数据库连接对象
    public static Connection getConnection() {
        
        if (con == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            return con;
        }
        return con;
    }
    
    public static PreparedStatement getPreparedStatement(String sql)
            throws SQLException {
        
        return getConnection().prepareStatement(sql);
    }
    
    public static PreparedStatement setPreparedStatementParam(
            PreparedStatement statement, Object obj[]) throws SQLException {
        
        for (int i = 0; i < obj.length; i++) {
            statement.setObject(i + 1, obj[i]);
        }
        return statement;
    }
    
    // 释放资源
    public static void release(PreparedStatement ps, ResultSet rs) {
        try {
            if (con != null) {
                con.close();
                con = null;
            }
            if (ps != null) {
                ps.close();
                ps = null;
            }
            if (rs != null) {
                rs.close();
                rs = null;
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    
}
package com.mexue.middle.school.jdbc;

import com.hspedu.helper.JdbcDaoHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Demo1 {
    
  
    
    public static void main(String[] args) throws Exception {
        
        Connection conn = JdbcDaoHelper.getConnection();
        
        String tableName = "school";
        
        PreparedStatement ps = conn.prepareStatement("select * from " + tableName);
        ResultSet rs = ps.executeQuery();
        ResultSetMetaData rsmd = rs.getMetaData();
        
        List<String> filter = new ArrayList<>();
        List<String> result = new ArrayList<>();
        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
            if (rsmd.getColumnTypeName(i).contains("BLOB")) {
                String columnLabel = rsmd.getColumnLabel(i);
                filter.add(columnLabel);
            } else {
                String columnName = rsmd.getColumnName(i);
                result.add(columnName);//字段名称
            }
        }
        DatabaseMetaData dmd = conn.getMetaData();
        String catalog = conn.getCatalog();
        System.out.println("catalog = " + catalog);
        ResultSet primaryKeyResultSet = dmd.getPrimaryKeys(null, null, tableName);
        if (primaryKeyResultSet.next()) {
            
            String pk = primaryKeyResultSet.getString(4);
            Object object = primaryKeyResultSet.getObject(4);
            System.out.println("object = " + object + ", type = " + object.getClass().getName());
            System.out.println("pk = " + pk);
        }
        while (primaryKeyResultSet.next()) {
            String primaryKeyColumnName = primaryKeyResultSet.getString("COLUMN_NAME");
            System.out.print("列名称：" + primaryKeyColumnName + "\t");
            String PK_NAME = primaryKeyResultSet.getString("PK_NAME");
            System.out.print("主键的名称：" + PK_NAME + "\t");
            String KEY_SEQ = primaryKeyResultSet.getString("KEY_SEQ");
            System.out.print("主键中的序列号：" + KEY_SEQ + "\t");
            System.out.println();
        }
        
        System.out.println("filter:" + filter);
        System.out.println("result:" + result);
    }
    
}

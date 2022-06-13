package com.mexue.middle.school.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Demo02Main {
    
    public static void main(String[] args) throws SQLException {
        String driverName = "oracle.jdbc.driver.OracleDriver";
        String url = "jdbc:oracle:thin:@110.42.135.194:1521:orcl";
        String user = "saveinterface_test";
        String password = "pass#word1";
        Connection connection = JDBCHelper.createConnection(driverName, url, user, password);
        // 获取表名称
        List<Object> objectList = getAllTableName(connection);
        for (Object object : objectList) {
            String tableName = String.valueOf(object);
            System.out.println("表==>" + tableName);
        }
        // 某个表的列描述
        tableNameForColumn(connection, "DP_ADDRESS");
        // 某个表的主键
        tableNameForPrimary(connection, "DP_ADDRESS");
        // 某个表的外键
        tableNameForForeginKey(connection, "DP_ADDRESS");
    }
    
    /**
     * @param connection
     * @return
     * @throws SQLException
     */
    public static List getAllTableName(Connection connection) throws SQLException {
        DatabaseMetaData dbMetaData = connection.getMetaData();
        // 只要表
        String[] types = {"TABLE"};
        String catalog = connection.getCatalog();
        ResultSet tabs = dbMetaData.getTables(catalog, null, null, types);
        List<Object> tables = new ArrayList<Object>();
        while (tabs.next()) {
            tables.add(tabs.getObject("TABLE_NAME"));
        }
        return tables;
    }
    
    /**
     * @param connection
     * @param tableName
     * @throws SQLException
     */
    private static void tableNameForColumn(Connection connection, String tableName) throws SQLException {
        DatabaseMetaData dbMetaData = connection.getMetaData();
        String catalog = connection.getCatalog();
        ResultSet colRet = dbMetaData.getColumns(catalog, "%", tableName, "%");
        while (colRet.next()) {
            String columnName = colRet.getString("COLUMN_NAME");
            String columnType = colRet.getString("TYPE_NAME");
            int dataSize = colRet.getInt("COLUMN_SIZE");
            int digits = colRet.getInt("DECIMAL_DIGITS");
            int nullable = colRet.getInt("NULLABLE");
            //
            System.out.print("名称<列名称>：" + columnName + "\t");
            System.out.print("数据类型<类型名称>：" + columnType + "\t");
            System.out.print("长度/集合<列的大小>：" + dataSize + "\t");
            System.out.print("小数部分的位数：" + digits + "\t");
            System.out.print("允许NULL<是否允许使用 NULL>：" + nullable + "\t");
            System.out.println();
        }
    }
    
    /**
     * @param connection
     * @param tableName
     * @throws SQLException
     */
    private static void tableNameForPrimary(Connection connection, String tableName) throws SQLException {
        DatabaseMetaData dbMetaData = connection.getMetaData();
        String catalog = connection.getCatalog();
        ResultSet primaryKeyResultSet = dbMetaData.getPrimaryKeys(catalog, null, tableName);
        while (primaryKeyResultSet.next()) {
            String primaryKeyColumnName = primaryKeyResultSet.getString("COLUMN_NAME");
            System.out.print("列名称：" + primaryKeyColumnName + "\t");
            String PK_NAME = primaryKeyResultSet.getString("PK_NAME");
            System.out.print("主键的名称：" + PK_NAME + "\t");
            String KEY_SEQ = primaryKeyResultSet.getString("KEY_SEQ");
            System.out.print("主键中的序列号：" + KEY_SEQ + "\t");
            System.out.println();
        }
    }
    
    /**
     * @param connection
     * @param tableName
     * @throws SQLException
     */
    private static void tableNameForForeginKey(Connection connection, String tableName) throws SQLException {
        DatabaseMetaData dbMetaData = connection.getMetaData();
        String catalog = connection.getCatalog();
        ResultSet foreignKeyResultSet = dbMetaData.getImportedKeys(catalog, null, tableName);
        while (foreignKeyResultSet.next()) {
            String fkColumnName = foreignKeyResultSet.getString("FKCOLUMN_NAM");
            String pkTablenName = foreignKeyResultSet.getString("PKTABLE_NAME");
            String pkColumnName = foreignKeyResultSet.getString("PKCOLUMN_NAME");
            System.out.print("列名称：" + fkColumnName + "\t");
            System.out.print("表名称：" + pkTablenName + "\t");
            System.out.print("列名称：" + pkColumnName + "\t");
            System.out.println();
        }
    }
    
}

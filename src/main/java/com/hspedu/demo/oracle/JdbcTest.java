package com.hspedu.demo.oracle;
// package com.infoservice.test;

import java.sql.*;


/*
echo %CLASSPATH%
echo $CLASSPATH

java -cp .;./*.jar  JdbcTest
java -cp .;./ojdbc14.jar  JdbcTest
java -cp .:./ojdbc14.jar  JdbcTest

*/


public class JdbcTest {
    public static void main(String[] args) {

        Connection conn = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;

        try{

            Class.forName("oracle.jdbc.driver.OracleDriver");
            
            /*
            // String url = "jdbc:oracle:thin:@10.17.31.120:1521:dmsdb";
            // String name = "dms_oem_test";
            // String pwd = "dms_oem_test";
            
            String url = "jdbc:oracle:thin:@10.17.18.96:1521:dmsdb";
            String name = "dms_oem_uat";
            String pwd = "OEM_UAT_UAT";


            
            String url = "jdbc:oracle:thin:@10.45.250.165:1521/frdmsprod";
            String name = "dms_oem_prod";
            String pwd = "GS_CHwrjw3";
            
            String url = "jdbc:oracle:thin:@10.45.250.165:1521/frdmsprod";       //   /frdmsprod  ok
            String url = "jdbc:oracle:thin:@10.45.250.165:1521:dmsprod";         //   :dmsprod  ok
            String name = "dms_oem_prod";   
            String pwd = "GS_CHwrjw3";
            

            */
            
         
            // jdbc:oracle:thin:@//<host>:<port>/<service_name> 
            // jdbc:oracle:thin:@<host>:<port>:<SID> 
            
            // 厂端
            // String url = "jdbc:oracle:thin:@10.40.123.158:1521/dms11g";
            // String name = "dms_oem_prod";
            // String pwd = "GS_#CHwrjw312";

            // 店端
            String url = "jdbc:oracle:thin:@10.40.123.158:1521/dms11g";
            String name = "dms_dealer_prod";
            String pwd = "KXG9s_#YZWz12";


            conn = DriverManager.getConnection(url,name,pwd);
            System.out.println("connect success");
      
            String sql = "SELECT  USER_ID, NAME FROM tc_user ";
          
		    pstat = conn.prepareStatement(sql);
            rs = pstat.executeQuery();
            System.out.println("excute success");

            while(rs.next()){
                System.out.println("1 "+ rs.getString(1) + "2 " + rs.getString(2));
            }
        }catch (ClassNotFoundException  e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if(rs != null){

                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if(pstat != null){

                try {
                    pstat.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if(conn != null){

                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

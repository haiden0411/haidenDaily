package com.huawei.oracleProcedure;

import oracle.jdbc.OracleTypes;

import java.sql.*;

/**
 * Author：胡灯
 * Date：2019-07-03 22:39
 * Description：<描述>
 */
public class ProcedurewithReturnlist {


    public static void main(String[] args) {

        String driver = "oracle.jdbc.driver.OracleDriver";
        String strUrl = "jdbc:oracle:thin:@127.0.0.1:1521:orcl ";
        Statement stmt = null;
        ResultSet rs = null;
        Connection conn = null;
        CallableStatement cstmt = null;

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(strUrl, "scott", "haiden");
            CallableStatement proc = null; //创建执行存储过程的对象
            proc = conn.prepareCall("{ call queryemplist (?)}"); //设置存储过程 call为关键字.
            proc.registerOutParameter(1, OracleTypes.CURSOR);//设置第二个输入参数
            proc.execute();//执行
            rs = (ResultSet) proc.getObject(1);
            while (rs.next()) {
                System.out.println(rs.getInt(1)+"----"+rs.getString(2)+"----"+rs.getString(3));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                    if (stmt != null) {
                        stmt.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                }
            } catch (SQLException ex1) {
            }
        }
    }
}


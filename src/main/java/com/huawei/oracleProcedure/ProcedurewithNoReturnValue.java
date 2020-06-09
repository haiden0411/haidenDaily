package com.huawei.oracleProcedure;

import java.sql.*;

/**
 * Author：胡灯
 * Date：2019-07-03 22:39
 * Description：<描述>
 */
public class ProcedurewithNoReturnValue {


    public static void main(String[] args) {

        String driver = "oracle.jdbc.driver.OracleDriver";
        String strUrl = "jdbc:oracle:thin:@127.0.0.1:1521:orcl ";
        Statement stmt = null;
        ResultSet rs = null;
        Connection conn = null;
        CallableStatement cstmt = null;
       /* eno number,  --输入参数，雇员编号
        name varchar2,--输入参数，雇员名称
        salary number,--输入参数，雇员薪水
        job VARCHAR2 DEFAULT 'CLERK',--输入参数，雇员工种默认'CLERK'
        dno number --输入参数，雇员部门编号*/

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(strUrl, "scott", "haiden");
            CallableStatement proc = null; //创建执行存储过程的对象
            proc = conn.prepareCall("{ call add_employee (?,?,?,?,?) }"); //设置存储过程 call为关键字.
            proc.setInt(1, 7934); //设置第一个输入参数
            proc.setString(2, "haiden");//设置第二个输入参数
            proc.setInt(3, 9000);//设置第二个输入参数
            proc.setString(4, "policeman");//设置第二个输入参数
            proc.setInt(5, 10);//设置第二个输入参数
            proc.execute();//执行
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


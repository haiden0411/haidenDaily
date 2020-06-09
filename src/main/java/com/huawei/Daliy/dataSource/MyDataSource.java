package com.huawei.Daliy.dataSource;
import javax.sql.DataSource;
import java.io.PrintWriter;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.logging.Logger;
/**
 * Author：胡灯
 * Date：2020-06-06 8:36
 * Description：<描述>
 */
public class MyDataSource implements DataSource
{
    private int initCount = 3;
    private int maxCount = 10;
    private int curCount = 0;
    private LinkedList<Connection> list = new LinkedList<>();
    public MyDataSource()
    {
        for (int i = 0; i < 3; i++)
        {
            Connection connection = createConnection();
            list.add(connection);
        }
    }
    private Connection createConnection(){

        try
        {
            Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.1.109:3307/test?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC", "root", "root");
            Connection proxyConn = (Connection) Proxy.newProxyInstance(MyDataSource.class.getClassLoader(),connection.getClass().getInterfaces(), (proxy, method, args) ->
            {
                System.out.println("invoke方法被调用了...." + method.getName());
                if (method.getName().equals("close"))
                {
                    list.addLast((Connection) proxy);
                    return null;
                }
                return method.invoke(connection,args);
            });
            curCount++;
            return proxyConn;
        }
        catch (SQLException e)
        {
            throw new  RuntimeException(e);
        }
    }

    @Override
    public Connection getConnection()
    {
        if (list.size()>0)
        {
            return list.removeFirst();
        }

        if (curCount < maxCount){
            Connection connection = createConnection();
            return connection;
        }

      throw new RuntimeException("连接池对象已经达到最大值，无法创建了，请等待");
    }
    @Override
    public Connection getConnection(String username, String password) throws SQLException
    {
        return null;
    }
    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException
    {
        return null;
    }
    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException
    {
        return false;
    }
    @Override
    public PrintWriter getLogWriter() throws SQLException
    {
        return null;
    }
    @Override
    public void setLogWriter(PrintWriter out) throws SQLException
    {
    }
    @Override
    public void setLoginTimeout(int seconds) throws SQLException
    {
    }
    @Override
    public int getLoginTimeout() throws SQLException
    {
        return 0;
    }
    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException
    {
        return null;
    }
}

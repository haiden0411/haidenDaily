package com.huawei.springboot.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationBeanFactoryMetadata;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
/**
 * Author：胡灯
 * Date：2020-06-02 21:40
 * Description：<描述>
 */
@Component
@ConfigurationProperties("haiden")
//@Scope("prototype")
public class ParamsBean
{
    private String jdbcUrl;
    private String password;
    private String username;

    public String getJdbcUrl()
    {
        return jdbcUrl;
    }
    public void setJdbcUrl(String jdbcUrl)
    {
        this.jdbcUrl = jdbcUrl;
    }
    public String getPassword()
    {
        return password;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
    public String getUsername()
    {
        return username;
    }
    public void setUsername(String username)
    {
        this.username = username;
    }
}

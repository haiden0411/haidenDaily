package com.huawei.springboot.config;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
/**
 * Author：胡灯
 * Date：2020-06-03 6:48
 * Description：<描述>
 */
@Component
public class DataSourceShow implements ApplicationContextAware
{
    ApplicationContext applicationContext = null;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
    {
        this.applicationContext = applicationContext;
        DataSource dataSource = applicationContext.getBean(DataSource.class);
        System.out.println("-----------------");
        System.out.println(dataSource.getClass().getName());
        System.out.println("-----------------");
    }
}

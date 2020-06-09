package com.huawei.springboot.domain;

/**
 * Author：胡灯
 * Date：2019-09-07 22:26
 * Description：<描述>
 */
public class Trader
{
    private final String name;
    private final String city;

    public Trader(String name, String city)
    {
        this.name = name;
        this.city = city;
    }

    public String getName()
    {
        return name;
    }

    public String getCity()
    {
        return city;
    }

    @Override
    public String toString()
    {
        return "Trader{" + "name='" + name + '\'' + ", city='" + city + '\'' + '}';
    }
}

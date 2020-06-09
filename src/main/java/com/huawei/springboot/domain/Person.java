package com.huawei.springboot.domain;

import com.google.common.collect.ComparisonChain;
import org.apache.commons.lang3.builder.ToStringBuilder;
/**
 * Author：胡灯
 * Date：2019-08-11 22:10
 * Description：<描述>
 */
public class Person implements Comparable<Person>
{
    private String name;
    private int age;
    private String addr;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public String getAddr()
    {
        return addr;
    }

    public void setAddr(String addr)
    {
        this.addr = addr;
    }

    @Override
    public int compareTo(Person o)
    {
        return ComparisonChain.start().compare(this.age, o.age).
                compare(this.addr, o.addr).compare(this.name, o.name).result();

    }
    @Override
    public String toString()
    {
       return new ToStringBuilder(this).append("name:",name).append("age",age).append("addr",addr).toString();
    }
}

package com.huawei.springboot.domain;

import com.google.common.collect.ComparisonChain;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
/**
 * Author：胡灯
 * Date：2019-08-11 22:10
 * Description：<描述>
 */
@Data
public class Person implements Comparable<Person>
{
    private String name;
    private int age;
    private String addr;



    @Override
    public int compareTo(Person o)
    {
        return ComparisonChain.start().compare(this.age, o.age).
                compare(this.addr, o.addr).compare(this.name, o.name).result();
    }
}

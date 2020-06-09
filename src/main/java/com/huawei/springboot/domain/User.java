package com.huawei.springboot.domain;

import com.google.common.base.Preconditions;
import com.jfinal.plugin.activerecord.Model;

import java.util.Objects;

/**
 * Author：胡灯
 * Date：2019-03-21 21:50
 * Description：<描述>
 */
public class User extends Model<User>
{

    public static  final User dao = new User();
    private int id;
    private String name;
    private int age;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = Preconditions.checkNotNull(name,"name不能为空");
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = Preconditions.checkElementIndex(age,200,"年龄超过200了");
    }

    public User()
    {
    }

    public User(int id, String name, int age)
    {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        User user = (User) o;
        return id == user.id && age == user.age && Objects.equals(name, user.name);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, name, age);
    }

    @Override
    public String toString()
    {
        return "User{" + "id=" + id + ", name='" + name + '\'' + ", age=" + age + '}';
    }
}

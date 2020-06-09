package com.huawei.springboot.domain;
import com.huawei.reflect.Column;
import com.huawei.reflect.Table;

@Table(name = "t_apple",value = "aaaaaa")
public class Apple
{
    @Column(value = "t_field")
    private String color;
    private Integer weight;

    public Apple()
    {
    }

    public Apple(String color, Integer weight)
    {
        this.color = color;
        this.weight = weight;
    }

    public String getColor()
    {
        return color;
    }

    public void setColor(String color)
    {
        this.color = color;
    }

    public Integer getWeight()
    {
        return weight;
    }

    public void setWeight(Integer weight)
    {
        this.weight = weight;
    }

    @Override
    public String toString()
    {
        return "Apple{" + "color='" + color + '\'' + ", weight=" + weight + '}';
    }
}

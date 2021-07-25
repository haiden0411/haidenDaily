package com.huawei.javaBase.optional;

import java.util.Optional;
/**
 * Author：胡灯
 * Date：2021-04-18 16:58
 * Description：<描述>
 */
public class Person
{
    private Car car;

    public Person(Car car)
    {
        this.car = car;
    }
    public Optional<Car> getCar()
    {
        return Optional.ofNullable(car);
    }
    public void setCar(Car car)
    {
        this.car = car;
    }
}

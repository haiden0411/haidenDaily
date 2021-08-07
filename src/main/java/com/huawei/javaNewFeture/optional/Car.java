package com.huawei.javaNewFeture.optional;

import java.util.Optional;
/**
 * Author：胡灯
 * Date：2021-04-18 16:58
 * Description：<描述>
 */
public class Car
{
    private Insurance insurance;
    public Car()
    {
    }
    public Car(Insurance insurance)
    {
        this.insurance = insurance;
    }
    public Optional<Insurance> getInsurance()
    {
        return Optional.ofNullable(insurance);
    }
    public void setInsurance(Insurance insurance)
    {
        this.insurance = insurance;
    }
}

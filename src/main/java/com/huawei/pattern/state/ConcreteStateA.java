package com.huawei.pattern.state;
/**
 * @Author：胡灯
 * @Date：2021-12-13 22:44
 * @Description：ConcreteStateA
 */
public class ConcreteStateA implements IState
{
    @Override
    public void handle()
    {
        System.out.println("SateA do action");
    }
}

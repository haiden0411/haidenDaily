package com.huawei.pattern.state;
/**
 * @Author：胡灯
 * @Date：2021-12-13 22:44
 * @Description：ConcreteStateB
 */
public class ConcreteStateB implements IState
{
    @Override
    public void handle()
    {
        System.out.println("StateB do action");
    }
}

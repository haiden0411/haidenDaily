package com.huawei.pattern.state;
/**
 * @Author：胡灯
 * @Date：2021-12-13 22:45
 * @Description：Context
 */
public class Context
{
    private IState currentState;
    public void setState(IState state)
    {
        this.currentState = state;
    }
    public void handle()
    {
        this.currentState.handle();
    }
}

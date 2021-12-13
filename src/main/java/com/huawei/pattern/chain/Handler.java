package com.huawei.pattern.chain;
/**
 * @Author：胡灯
 * @Date：2021-12-13 21:33
 * @Description：Handler
 */
public abstract class Handler
{
    protected Handler nextHandler;
    public void setNextHandler(Handler successor)
    {
        this.nextHandler = successor;
    }
    public abstract void handleRequest(String request);
}


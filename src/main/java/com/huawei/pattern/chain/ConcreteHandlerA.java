package com.huawei.pattern.chain;
/**
 * @Author：胡灯
 * @Date：2021-12-13 21:36
 * @Description：ConcreteHandlerA
 */
public class ConcreteHandlerA extends Handler
{
    @Override
    public void handleRequest(String request)
    {
        if ("requestA".equals(request))
        {
            System.out.println(this.getClass().getSimpleName()+" deal with request " +request);
        }
        if (this.nextHandler!=null)
        {
            this.nextHandler.handleRequest(request);
        }
    }
}

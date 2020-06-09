package com.huawei.suanfa;
/**
 * Author：胡灯
 * Date：2020-03-22 11:34
 * Description：<描述>
 */
public class MyNode
{
    String data;
    MyNode next;
    public MyNode()
    {
    }
    public MyNode(String data)
    {
        this.data = data;
        this.next = null;
    }
    public String getData()
    {
        return data;
    }
    public void setData(String data)
    {
        this.data = data;
    }
    public MyNode getNext()
    {
        return next;
    }
    public void setNext(MyNode next)
    {
        this.next = next;
    }
}

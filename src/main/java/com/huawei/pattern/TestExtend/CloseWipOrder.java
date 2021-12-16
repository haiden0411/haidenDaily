package com.huawei.pattern.TestExtend;
/**
 * @Author：胡灯
 * @Date：2021-12-16 22:30
 * @Description：CloseWipOrder
 */
public class CloseWipOrder
{
    public void process()
    {
        testA();
    }

    protected void testA()
    {
        testB();
        testC();
        testD();
        testE();
    }
    protected void testB()
    {
        System.out.println("CloseWipOrder.testB");
    }
    protected void testC()
    {
        System.out.println("CloseWipOrder.testC");
    }
    protected void testD()
    {
        System.out.println("CloseWipOrder.testD");
    }
    protected void testE()
    {
        System.out.println("CloseWipOrder.testE");
    }

}

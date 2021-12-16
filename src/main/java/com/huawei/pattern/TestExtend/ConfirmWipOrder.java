package com.huawei.pattern.TestExtend;
/**
 * @Author：胡灯
 * @Date：2021-12-16 22:31
 * @Description：子类重写了Protected方法，运行时就执行子类的方法
 */
public class ConfirmWipOrder extends CloseWipOrder
{
    public void process1()
    {
        super.testA();
    }

    @Override
    protected void testB()
    {
        System.out.println("ConfirmWipOrder.testB");
    }
    @Override
    protected void testC()
    {
        System.out.println("ConfirmWipOrder.testC");
    }
    @Override
    protected void testD()
    {
        System.out.println("ConfirmWipOrder.testD");
    }
}

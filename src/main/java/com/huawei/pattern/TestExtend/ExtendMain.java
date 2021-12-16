package com.huawei.pattern.TestExtend;
/**
 * @Author：胡灯
 * @Date：2021-12-16 22:36
 * @Description：ExtendMain
 */
public class ExtendMain
{
    public static void main(String[] args)
    {
        CloseWipOrder closeWipOrder = new CloseWipOrder();
        closeWipOrder.process();
        System.out.println("===========================");
        ConfirmWipOrder confirmWipOrder = new ConfirmWipOrder();
        confirmWipOrder.process1();
    }
}

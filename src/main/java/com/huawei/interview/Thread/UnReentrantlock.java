package com.huawei.interview.Thread;
/**
 * Author：胡灯
 * Date：2020-05-24 12:00
 * Description：<描述>
 */
public class UnReentrantlock
{
    private volatile boolean isLocked = false;

    public synchronized void lock() throws InterruptedException
    {
        System.out.println("进入lock加锁:"+Thread.currentThread().getName());
        while (isLocked)
        {
            System.out.println("进入wait等待状态:"+Thread.currentThread().getName());
            wait();
        }
        isLocked = true;
    }

    public synchronized void unlock(){
        System.out.println("进入unlock加锁::"+Thread.currentThread().getName());
        isLocked =false;
        notify();
    }
}

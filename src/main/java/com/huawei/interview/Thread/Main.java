package com.huawei.interview.Thread;
/**
 * Author：胡灯
 * Date：2020-05-24 12:07
 * Description：<描述>
 */
public class Main
{
    private UnReentrantlock lock = new UnReentrantlock();

    public void methodA(){
        try
        {
            lock.lock();
            System.out.println("methodA被调用");
            methodB();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }finally
        {
            lock.unlock();
        }
    }
    public void methodB()
    {
        try
        {
            lock.lock();
            System.out.println("methodB被调用");
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }finally
        {
            lock.unlock();
        }
    }
    public static void main(String[] args)
    {
        new Main().methodA();
    }
}

package com.huawei.daily;/**
 * Author：胡灯
 * Date：2021-11-06 19:34
 * Description：<描述>
 */
import lombok.SneakyThrows;
import org.junit.Test;

import java.io.OutputStream;
import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
/**
 * @author Administrator
 * @description: TestThread
 * @date 2021/11/6 19:34
 */
public class TestThread
{
    public static final int SLEEP_GAP = 5000;//睡眠时长
    public static final int MAX_TURN = 50;//睡眠次数
    static class SleepThread extends Thread
    {
        static int threadSeqNumber = 1;
        public SleepThread()
        {
            super("sleepThread-" + threadSeqNumber);
            threadSeqNumber++;
        }
        public void run()
        {
            try
            {
                System.out.println(getName() + " 进入睡眠.");
                // 线程睡眠一会
                Thread.sleep(SLEEP_GAP);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
                System.out.println(getName() + " 发生被异常打断.");
                return;
            }
            System.out.println(getName() + " 运行结束.");
        }
    }
    public static void main(String args[]) throws InterruptedException
    {
        Thread thread1 = new SleepThread();
        thread1.start();
        Thread thread2 = new SleepThread();
        thread2.start();
        sleepSeconds(2);                       //主线程等待2秒
        thread1.interrupt();   //打断线程1
        sleepSeconds(5);                       //主线程等待5秒
        thread2.interrupt();   //打断线程2，此时线程2已经终止
        sleepSeconds(1);                       //主线程等待1秒
        System.out.println("程序运行结束.");
    }
    private static void sleepSeconds(int i)
    {
        try
        {
            TimeUnit.SECONDS.sleep(i);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
    
    @Test
    public void testInterupt() throws InterruptedException
    {
        Thread thread = new Thread()
        {
            @Override
            public void run()
            {
                System.out.println("线程启动了");
                while (true)
                {
                    System.out.println(LocalDateTime.now()+"--"+isInterrupted());
                    try
                    {
                        Thread.sleep(5000);
                        System.out.println("线程运行中");
                    }
                    catch (InterruptedException e)
                    {
                        System.out.println("aa");
                        e.printStackTrace();
                    }
                    if (isInterrupted())
                    {
                        System.out.println("线程中断了");
                        return;
                    }
                }
            }
        };

    thread.start();
    TimeUnit.SECONDS.sleep(2);
    thread.interrupt();
    TimeUnit.SECONDS.sleep(2);
    thread.interrupt();
    TimeUnit.SECONDS.sleep(5);
    }
}
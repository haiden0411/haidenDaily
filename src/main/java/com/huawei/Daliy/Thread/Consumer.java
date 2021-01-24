package com.huawei.Daliy.Thread;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
/**
 * Author：胡灯
 * Date：2019-12-21 21:55
 * Description：<描述>
 */
public class Consumer implements Runnable
{
    private String name;
    private BlockingQueue<Data> queue;
    private Random random = new Random();
    public Consumer(String name, BlockingQueue<Data> queue)
    {
        this.name = name;
        this.queue = queue;
    }
    @Override
    public void run()
    {
        try
        {
            while (true)
            {
               Data data =  this.queue.poll(5, TimeUnit.SECONDS);
                if (data == null)
                {
                    System.out.println("当前消费者" + name + "超过5s没有接收到数据，所以退出");
                    break;
                }
                Thread.sleep(1000*random.nextInt(5));
                System.out.println("当前消费者" + name + "成功消费，消费id为：" + data.getId());
            }
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}

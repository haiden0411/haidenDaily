package com.huawei.Daliy.Thread;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
/**
 * Author：胡灯
 * Date：2019-12-21 21:55
 * Description：<描述>
 */
public class Provider implements Runnable
{
    private BlockingQueue<Data> queue;
    private String name;
    private volatile  boolean isRunning = true;
    private Random random = new Random();
    private static AtomicInteger count = new AtomicInteger();
    public Provider(BlockingQueue<Data> queue,String name)
    {
        this.queue = queue;
        this.name = name;
    }
    @Override
    public void run()
    {
        while (isRunning)
        {
            try
            {
                Thread.sleep(1000*random.nextInt(5));
                int id = count.incrementAndGet();
                Data data = new Data(id,"数据"+id);
                if (!this.queue.offer(data,2, TimeUnit.SECONDS))
                {
                    System.out.println("生产者" + name + "将数据" + data.getName() + "放入队列中超时");
                }else {
                    System.out.println("生产者" + name + "创建了数据id:" + data.getId() + ",并成功将数据放入队列");
                }

            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void stop (){
       this.isRunning = false;
    }
}

package com.huawei.Daliy.Thread;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
/**
 * Author：胡灯
 * Date：2019-12-21 22:38
 * Description：<描述>
 */
public class Main
{
    public static void main(String[] args)
    {
        BlockingQueue<Data> queue = new LinkedBlockingQueue<>(10);
        //生产
        Provider p1 = new Provider(queue, "p1");
        Provider p2 = new Provider(queue, "p2");
        Provider p3 = new Provider(queue, "p3");
        Provider p4 = new Provider(queue, "p4");
        Provider p5 = new Provider(queue, "p5");
        //消费
        Consumer c1 = new Consumer("c1",queue);
        Consumer c2 = new Consumer("c2",queue);
        Consumer c3 = new Consumer("c3",queue);
        Consumer c4 = new Consumer("c4",queue);
        Consumer c5 = new Consumer("c5",queue);
        ExecutorService es = Executors.newFixedThreadPool(8);

        es.submit(p1);
        es.submit(p2);
        es.submit(p3);
        es.submit(p4);
        es.submit(p5);

        es.submit(c1);
        es.submit(c2);
        es.submit(c3);
        es.submit(c4);
        es.submit(c5);
        try
        {
            Thread.sleep(3000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        p1.stop();
        System.out.println("生产者p1停止生产数据");
        p2.stop();
        System.out.println("生产者p2停止生产数据");
        p3.stop();
        System.out.println("生产者p3停止生产数据");

        es.shutdown();
    }
}

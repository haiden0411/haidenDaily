package com.huawei.javaNewFeture.CompletableFuture;

import com.huawei.springboot.utils.Java8Utils;
import lombok.SneakyThrows;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * Author：胡灯
 * Date：2019-09-24 22:29
 * Description：<描述>
 */
public class TestCompletableFuture
{


    public static void main(String[] args)
    {
        long start = System.currentTimeMillis();
        System.out.println(Java8Utils.findPriceAsync("myPhone27s",Java8Utils.executor));
        //System.out.println(findPrices("myPhone27s"));
        long duration = System.currentTimeMillis() - start;
        System.out.println("Done in "+ duration + " ms");
    }


    private static void doSomethingElse(){
        System.out.println("do other things");
    }

    private static long doSomethingLongTime(){
        try
        {
            System.out.println("do long time job");
            Thread.sleep(3000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        return 100L;
    }
    @Test
    public void testWhenComplete() throws ExecutionException, InterruptedException
    {
       // ExecutorService excutor = Executors.newFixedThreadPool(2);
        CompletableFuture<Void> future = CompletableFuture.runAsync(() ->
        {
            sleepSecond(3);
            //System.out.println("方法完成");
            print("抛出异常");
            throw new RuntimeException("发生异常");
        });

        future.whenComplete((unused, throwable) -> {
           // print(throwable.getMessage());
            print("执行完成");
        });

        future.exceptionally(throwable -> {
            print("执行失败");
            return null;
        });
        /*future.handle((unused, throwable) -> {
            if (throwable==null)
            {
                System.out.println("没有发生异常");
            }else {
                System.out.println("发生异常");
            }
            return null;
        });*/

        print("test aa");
        sleepSecond(5);
       // future.cancel(true);
    }
    @SneakyThrows
    private void sleepSecond(int second)
    {
        Thread.sleep(second*1000);
    }
    private void print(String str)
    {
        System.out.println(Thread.currentThread()+":"+str);
    }


}

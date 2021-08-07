package com.huawei.javaNewFeture.CompletableFuture;

import com.huawei.springboot.utils.Java8Utils;
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


}

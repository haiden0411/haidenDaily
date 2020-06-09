package com.huawei;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
/**
 * Author：胡灯
 * Date：2020-05-21 21:24
 * Description：<描述>
 */
public class TestCompleteFuture
{
    @Test
    public void test01()
    {
        //直接创建任务，并设置返回值
        CompletableFuture<String> task1 = CompletableFuture.completedFuture("hi");
        //创建一个不带返回值的异步执行任务
        CompletableFuture<Void> task2 = CompletableFuture.runAsync(() ->
        {
            System.out.println("hi");
        });
        //创建一个带有返回值的异步执行任务
        CompletableFuture.supplyAsync(() -> {
            return "hi";
        });

    }

    @Test
    public void test02(){
        String result = null;
        try
        {
            result = CompletableFuture.completedFuture("hi").get();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        catch (ExecutionException e)
        {
            e.printStackTrace();
        }
        System.out.println(result);

        try
        {
            String hi = CompletableFuture.completedFuture("hi").get(10, TimeUnit.SECONDS);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        catch (ExecutionException e)
        {
            e.printStackTrace();
        }
        catch (TimeoutException e)
        {
            e.printStackTrace();
        }
    }
    //设置任务执行过程抛出异常时，返回的默认值
    @Test
    public void test03() throws ExecutionException, InterruptedException
    {
        CompletableFuture<Integer> task = CompletableFuture.supplyAsync(() ->
        {
            return Integer.parseInt("1a");
        }).exceptionally(throwable ->
        {
            throwable.printStackTrace();
            return 999;
        });
        System.out.println(task.get());
    }

    //设置任务完成后的动作，不管任务是以正常执行完成的方式来结束，还是以抛出异常的方式来结束，该动作都会执行
    @Test
    public void test04(){
        CompletableFuture.supplyAsync(() -> {
            return Integer.parseInt("1a");
        }).whenComplete((res, ex) -> {
            if (ex==null)
            {
                System.out.println("任务完成,result="+res);
            }
            System.out.println("过程发生异常");
            ex.printStackTrace();
        });

    }
    @Test
    public void test05() throws Exception
    {
        CompletableFuture<Integer> task1 = CompletableFuture.supplyAsync(() -> {
            return Integer.parseInt("1");
        });
        Thread.sleep(1000);
        boolean complete = task1.complete(1);
        System.out.println(complete);
        System.out.println(task1.get());

        CompletableFuture<Integer> task2 = CompletableFuture.supplyAsync(() -> {
            return Integer.parseInt("2");
        });
        //Thread.sleep(1000);
        boolean setIsSuccess2 = task2.completeExceptionally(new RuntimeException("Manual Except End"));
        System.out.println(setIsSuccess2);

        CompletableFuture<Void> task3 = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
                System.out.println("over");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        //task3.join();
        /*boolean cancelSuccess = task3.cancel(true);
        System.out.println("cancelSuccess:" + cancelSuccess);*/
    }

    @Test
    public void test06() throws Exception
    {
        CompletableFuture<Void> task1 = CompletableFuture.runAsync(() ->
        {
            delay(5);
        });
        boolean cancel = task1.cancel(true);
        System.out.println(cancel);
    }
    //在现有任务的基础上，设置对该任务结果进行处理的结果处理任务
    @Test
    public void test07(){
        CompletableFuture<String> task = CompletableFuture.supplyAsync(() ->
        {
            //System.out.println(1/0);
            return "hello,world";
        });
        CompletableFuture<Integer> handle = task.handle((res, ex) ->
        {
            if (ex == null)
            {
                System.out.println("result:" + res);
                return 0;
            }
            ex.printStackTrace();
            return -1;
        });
        System.out.println("The result code of task is " + handle.join());
    }
    //设置任务的后续处理流程，其中后续处理流程会以一个新的任务存在
    @Test
    public void test08(){
        CompletableFuture<String> task1 = CompletableFuture.completedFuture("abc");
        CompletableFuture<String> tast2 = CompletableFuture.completedFuture("cde");
        CompletableFuture<Void> nexttask1 = task1.thenRun(() ->
        {
            System.out.println("done");
        });

        CompletableFuture<Void> nexttask2 = task1.runAfterBoth(task1, () ->
        {
            System.out.println("task1,tas2,all done");
        });

        CompletableFuture<Void> nexttask3 = task1.thenAccept(s ->
        {
            System.out.println(s);
        });

        CompletableFuture<Void> nexttask4 = task1.thenAcceptBoth(tast2, (x, y) ->
        {
            System.out.println(x + y);
        });

        CompletableFuture<String> nexttask5 = task1.thenApply(s ->
        {
            return s.toUpperCase();
        });
        CompletableFuture<String> nexttask6 = task1.thenCompose(s ->
        {
            return CompletableFuture.supplyAsync(() ->
            {
                return s.toUpperCase();
            });
        });
        CompletableFuture<String> nexttask7 = task1.thenCombine(tast2, (x, y) ->
        {
            return x + y;
        });

    }

    @Test
    public void test09(){
        CompletableFuture<Integer> task = CompletableFuture.supplyAsync(() ->
        {
            delay(1);
            return Integer.parseInt("99");
        });
        delay(2);
        System.out.println(task.isCompletedExceptionally());
        System.out.println(task.isDone());
        System.out.println(task.isCancelled());
    }
    private void delay(int n){
        try
        {
            TimeUnit.SECONDS.sleep(n);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}

package com.huawei.javaNewFeture.CompletableFuture;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
/**
 * Author：胡灯
 * Date：2021-05-23 23:22
 * Description：<描述>
 */
public class CompleteFutureDemo
{
    public static void main(String[] args) throws ExecutionException, InterruptedException
    {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        CompletableFuture<Boolean> future = new CompletableFuture<>();
        List<CompletableFuture<Boolean>> futureList = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();

        for (int i = 1; i <= 10; i++)
        {
            int a = i;
           CompletableFuture<Boolean> future1 = CompletableFuture.supplyAsync(() -> {
                try
                {
                    return doSomething(a);
                }
                catch (Exception e)
                {
                    future.completeExceptionally(e);
                }
                return false;
            },executor);
            futureList.add(future1);
        }

       future.thenApply(aBoolean -> aBoolean).exceptionally(throwable -> {
            for (CompletableFuture<Boolean> booleanCompletableFuture : futureList)
            {
                booleanCompletableFuture.complete(false);
            }
            map.put("error",throwable.getCause().getMessage());
            return false;
        });
        List<Boolean> collect = futureList.stream().map(CompletableFuture::join).collect(Collectors.toList());
        if (collect.contains(Boolean.FALSE))
        {
            throw new RuntimeException(map.get("error").toString());
        }
    }
    private static boolean doSomething(int n)
    {
        System.out.println("第"+n+"个线程开始了");
        dealay(n);
        if (n==2)
        {
            throw new RuntimeException("occur a exeption");
        }
        System.out.println("第"+n+"个线程结束了");
        return true;
    }

    private static void dealay(int n)
    {
        try
        {
            Thread.sleep(n*1000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
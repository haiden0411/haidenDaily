package com.huawei.Daliy.Thread;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
/**
 * Author：胡灯
 * Date：2020-05-21 23:26
 * Description：<描述>
 */
public class StreamTestFuture
{
    private static  void delay(int n){
        try
        {
            TimeUnit.SECONDS.sleep(n);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
    public static String rpcCall(String ip,String param){
        System.out.println(ip + " rpcCall " + param);
        delay(new Random().nextInt(5));
        return param;
    }

    public static void main(String[] args)
    {
        List<String> ips = new ArrayList<>();
        ExecutorService excutors = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 100; i++)
        {
            ips.add("192.168.1."+i);
        }
        long start = System.currentTimeMillis();
       /* List<String> result = new ArrayList<>();
        ips.forEach(s ->
        {
            result.add(rpcCall(s,s));
        });
        result.forEach(System.out::println);*/
        List<CompletableFuture<String>> futures = ips.stream().map(s -> CompletableFuture.supplyAsync(() -> rpcCall(s, s),excutors)).collect(Collectors.toList());
        List<String> resultlist = futures.stream().map(s -> s.join()).collect(Collectors.toList());
         //futures.stream().map(s -> s.join());
        resultlist.forEach(System.out::println);
        long end = System.currentTimeMillis();
        System.out.println("task time:" + (end - start)+" msec");
        excutors.shutdown();

        List<CompletableFuture<Void>> future2 = new ArrayList<>();
    }
}

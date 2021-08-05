package com.huawei.Daliy.threadLocal;
import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
/**
 * Author：胡灯
 * Date：2021-08-04 23:00
 * Description：threadLocal三个线程各一份，互不干扰
 */
public class DataSourceContentHolderTest
{
    public static void main(String[] args)
    {
        CompletableFuture<Void> c1 =  CompletableFuture.runAsync(() -> {
            DataSourceContentHolder.setId(123L);
            try
            {
                Thread.sleep(3000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        });
        CompletableFuture<Void> c2 = CompletableFuture.runAsync(() ->
        {
            DataSourceContentHolder.setId(456L);
            try
            {
                Thread.sleep(3000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        });
        List<CompletableFuture<Void>> completableFutures = Arrays.asList(c1, c2);
        completableFutures.stream().map(CompletableFuture::join);
        System.out.println(DataSourceContentHolder.getId());
    }
}

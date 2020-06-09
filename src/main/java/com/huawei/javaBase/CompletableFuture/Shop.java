package com.huawei.javaBase.CompletableFuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * Author：胡灯
 * Date：2019-09-24 23:01
 * Description：<描述>
 */
public class Shop
{

    private String name;

    public Shop(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public double getPrice(String product)
    {
        return calculatePricce(product);
    }

    public Future<Double> getPriceAsync(String product)
    {

        return CompletableFuture.supplyAsync(() -> calculatePricce(product));
    }

    private double calculatePricce(String product)
    {
        delay();
        return new Random().nextDouble() * product.charAt(0) + product.charAt(1);
    }

    public static void delay()
    {
        try
        {
            Thread.sleep(1000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}

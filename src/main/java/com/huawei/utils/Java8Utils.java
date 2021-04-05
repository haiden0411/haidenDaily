package com.huawei.utils;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.huawei.javaBase.CompletableFuture.Shop;
import com.huawei.javaBase.paralleStream.Accumulator;
import com.huawei.javaBase.paralleStream.FokJoinSumCalculator;
import org.assertj.core.util.Lists;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.ThreadFactory;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * Author：胡灯
 * Date：2019-09-14 8:04
 * Description：<描述>
 */
public class Java8Utils
{
    public static long sequentialSum(long n)
    {
        return Stream.iterate(1L, i -> i + 1).limit(n).reduce(0L, Long::sum);
    }

    public static long rangSum(long n)
    {
        return LongStream.rangeClosed(1L, 10_000_000).reduce(0L, Long::sum);
    }

    public static List<Shop> shops = Arrays
            .asList(new Shop("Best Price"),
                    new Shop("LetsSaveLog"),
                    new Shop("My favorite shoe"),
                    new Shop("BUYItAll"),
                    new Shop("haiden"),
                    new Shop("gree"),
                    new Shop("hengfaling"),
                    new Shop("chunxiang"),
                    new Shop("chunxiang1"),
                    new Shop("chunxiang2"),
                    new Shop("chunxiang3"),
                    new Shop("chunxiang4"),
                    new Shop("chunxiang5"),
                    new Shop("chunxiang6"),
                    new Shop("chunxiang7"),
                    new Shop("chunxiang8"),
                    new Shop("chunxiang9"),
                    new Shop("heli"));

    public static  Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(), 100), new ThreadFactory()
    {
        @Override
        public Thread newThread(Runnable r)
        {
            Thread thread = new Thread(r);
            thread.setDaemon(true);
            return thread;
        }
    });

    public static long parallRangSum(long n)
    {
        return LongStream.rangeClosed(1L, 10_000_000).parallel().reduce(0L, Long::sum);
    }

    public static long parallelSum(long n)
    {
        return Stream.iterate(1L, i -> i + 1).limit(n).parallel().reduce(0L, Long::sum);
    }

    public static long iterativeSum(long n)
    {
        long sum = 0;
        for (long i = 0; i <= n; i++)
        {
            sum += i;
        }
        return sum;
    }

    public static long sideEffectSum(long n)
    {
        Accumulator acc = new Accumulator();
        LongStream.rangeClosed(1, n).forEach(acc::add);
        return acc.total;
    }

    public static long forkJoinSum(long n)
    {
        long[] numbers = LongStream.rangeClosed(1, n).toArray();
        ForkJoinTask<Long> task = new FokJoinSumCalculator(numbers);
        return new ForkJoinPool().invoke(task);
    }

    public static int countWordsIteratively(String s)
    {
        int count = 0;
        boolean lastSpace = false;
        for (char c : s.toCharArray())
        {
            if (Character.isWhitespace(c))
            {
                lastSpace = true;
            }
            else
            {
                if (lastSpace)
                {
                    count++;
                }
                lastSpace = false;
            }
        }
        return count;

    }

    public static long measureSumPerf(Function<Long, Long> adder, long n)
    {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++)
        {
            long start = System.nanoTime();
            long sum = adder.apply(n);
            long duration = (System.nanoTime() - start) / 1_000_000;
            System.out.println("Result: " + sum);
            if (duration < fastest)
                fastest = duration;
        }
        return fastest;
    }

    public static List<String> findPrices(String product){
        return shops.parallelStream()
                .map(shop -> String.format("%s price is %.2f",shop.getName(),shop.getPrice(product)))
                .collect(Collectors.toList());

    }

    public static List<String> findPriceAsync(String product){
        List<CompletableFuture<String>> priceFuture = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(
                        () -> shop.getName() + " price is " + shop.getPrice(product)))
                .collect(Collectors.toList());
        return priceFuture.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }
    public static List<String> findPriceAsync(String product, Executor executor){
        List<CompletableFuture<String>> priceFuture = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(
                        () -> shop.getName() + " price is " + shop.getPrice(product),executor))
                .collect(Collectors.toList());
        return priceFuture.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }
    /**
     * 取配置文件的值，转成list
     * @param name
     * @return
     */
    public static List<String> getConfigList(String name){
        ResourceBundle config = ResourceBundle.getBundle("config");
        return config.keySet()
                .stream()
                .peek(s -> System.out.println(s))
                .filter(s -> Objects.equals(s, name))
                .map(s -> Lists.newArrayList(Splitter.on(",").omitEmptyStrings()
                        .trimResults().split(config.getString(s))))
                .findFirst()
                .orElseGet(Lists::newArrayList);
    }
    /**
     * 取配置文件的值
     * @param name
     * @return
     */
    public static String getProperty(String name)
    {
        ResourceBundle config = ResourceBundle.getBundle("config");
        return config.keySet()
                .stream()
                .filter(s -> Objects.equals(s, name))
                .map(config::getString)
                .findFirst()
                .orElseGet(() -> "unkown");
    }
    public static LocalDate mill2LocalDate(long millis)
    {
       return Instant.ofEpochMilli(millis).atZone(ZoneId.systemDefault()).toLocalDate();
    }
    public static Date LocalDate2Date(LocalDate localDate)
    {
        return Date.from(localDate.atStartOfDay().toInstant(ZoneOffset.of("+8")));
    }
    public static LocalDate date2LocalDate(Date date)
    {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }


}

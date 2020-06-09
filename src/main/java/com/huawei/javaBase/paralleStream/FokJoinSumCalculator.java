package com.huawei.javaBase.paralleStream;

import java.util.concurrent.RecursiveTask;

/**
 * Author：胡灯
 * Date：2019-09-20 23:14
 * Description：<描述>
 */
public class FokJoinSumCalculator extends RecursiveTask<Long>
{
    private final long[] numbers;
    private final int start;
    private final int end;
    public static final long THRESHOLD = 10_000;

    public FokJoinSumCalculator(long[] numbers)
    {
        this(numbers, 0, numbers.length);
    }

    private FokJoinSumCalculator(long[] numbers, int start, int end)
    {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute()
    {
        int length = end - start;
        if (length < THRESHOLD)
        {
           return  computeSequentially();
        }
        FokJoinSumCalculator leftTask = new FokJoinSumCalculator(numbers, start, start + length / 2);
        leftTask.fork();
        FokJoinSumCalculator rightTask = new FokJoinSumCalculator(numbers, start + length / 2, end);
        Long rightResult = rightTask.compute();
        Long leftResult = leftTask.join();
        return rightResult+leftResult;
    }

    private long computeSequentially()
    {
        long sum = 0;
        for (int i = start; i < end; i++)
        {
            sum += numbers[i];
        }
        return sum;
    }
}

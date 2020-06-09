package com.huawei.springboot.domain;

/**
 * Author：胡灯
 * Date：2019-09-07 22:27
 * Description：<描述>
 */
public class Transaction
{
    private final Trader trader;
    private final int year;
    private final int value;

    public Transaction(Trader trader, int year, int value)
    {
        this.trader = trader;
        this.year = year;
        this.value = value;
    }

    public Trader getTrader()
    {
        return trader;
    }

    public int getYear()
    {
        return year;
    }

    public int getValue()
    {
        return value;
    }

    @Override
    public String toString()
    {
        return "Transaction{" + "trader=" + trader + ", year=" + year + ", value=" + value + '}';
    }
}

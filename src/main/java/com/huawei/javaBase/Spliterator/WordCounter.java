package com.huawei.javaBase.Spliterator;

/**
 * Author：胡灯
 * Date：2019-09-21 23:14
 * Description：<描述>
 */
public class WordCounter
{
    private int count = 0;
    private boolean lastSpace = false;

    public WordCounter(int count,boolean lastSpace)
    {
        this.count = count;
        this.lastSpace = lastSpace;
    }
}

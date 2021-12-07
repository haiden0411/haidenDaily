package com.huawei.pattern.singleton;
/**
 * Author：胡灯
 * Date：2021-12-06 22:26
 * Description：<描述>
 */
public class ThreadLocalSingleton
{
    private static final ThreadLocal<ThreadLocalSingleton> instance = new ThreadLocal<ThreadLocalSingleton>()
    {
        @Override
        protected ThreadLocalSingleton initialValue()
        {
            return new ThreadLocalSingleton();
        }
    };
    private ThreadLocalSingleton()
    {
    }
    public static ThreadLocalSingleton getInstance()
    {
        return instance.get();
    }
}

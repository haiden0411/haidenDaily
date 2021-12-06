package com.huawei.pattern.singleton;
/**
 * Author：胡灯
 * Date：2021-12-04 23:14
 * Description：<描述>
 */
public class ExectorThread implements Runnable
{
    @Override
    public void run()
    {
        LazyDoubleCheckSingleton instance = LazyDoubleCheckSingleton.getInstance();
        System.out.println(Thread.currentThread().getName() + ":" + instance);
    }
}

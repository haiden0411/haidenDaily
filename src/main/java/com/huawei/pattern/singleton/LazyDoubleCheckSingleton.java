package com.huawei.pattern.singleton;
/**
 * Author：胡灯
 * Date：2021-12-04 23:51
 * Description：<描述>
 */
public class LazyDoubleCheckSingleton
{
    private volatile static LazyDoubleCheckSingleton instance;
    private LazyDoubleCheckSingleton()
    {
    }
    public static LazyDoubleCheckSingleton getInstance()
    {
        if (instance==null)
        {
            synchronized (LazyDoubleCheckSingleton.class)
            {
                instance = new LazyDoubleCheckSingleton();
            }
        }
        return instance;
    }
}

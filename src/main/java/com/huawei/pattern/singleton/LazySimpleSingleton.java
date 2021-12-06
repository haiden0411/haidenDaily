package com.huawei.pattern.singleton;
/**
 * Author：胡灯
 * Date：2021-12-04 23:10
 * Description：LazySimpleSingleton
 */
public class LazySimpleSingleton
{
    private static LazySimpleSingleton lazy = null;
    private LazySimpleSingleton()
    {
    }
    public static synchronized LazySimpleSingleton getInstance()
    {
        if (lazy == null)
        {
            lazy = new LazySimpleSingleton();
        }
        return lazy;
    }
}

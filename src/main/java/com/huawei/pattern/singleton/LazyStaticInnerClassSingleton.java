package com.huawei.pattern.singleton;
/**
 * Author：胡灯
 * Date：2021-12-05 0:16
 * Description：<描述>
 */
public class LazyStaticInnerClassSingleton
{
    private LazyStaticInnerClassSingleton()
    {
        if (LazyHolder.instance != null)
        {
            throw new RuntimeException("不允许创建多个实例");
        }
    }
    public static LazyStaticInnerClassSingleton getInstance()
    {
        return LazyHolder.instance;
    }
    private static class LazyHolder
    {
        private static final LazyStaticInnerClassSingleton instance = new LazyStaticInnerClassSingleton();
    }
}

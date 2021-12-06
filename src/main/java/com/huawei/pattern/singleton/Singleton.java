package com.huawei.pattern.singleton;
/**
 * Author：胡灯
 * Date：2021-12-04 22:35
 * Description：单例
 */
public class Singleton
{
    private static final Singleton instance = new Singleton();
    private Singleton(){}
    public static Singleton getInstance()
    {
        return instance;
    }
}

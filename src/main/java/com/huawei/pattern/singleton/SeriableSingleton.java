package com.huawei.pattern.singleton;
import java.io.Serializable;
/**
 * Author：胡灯
 * Date：2021-12-06 22:00
 * Description：<描述>
 */
public class SeriableSingleton implements Serializable
{
    private SeriableSingleton()
    {
    }
    private static SeriableSingleton instance = new SeriableSingleton();
    public static SeriableSingleton getInstance()
    {
        return instance;
    }
    private Object readResolve()
    {
        return instance;
    }
}

package com.huawei.pattern.singleton;
/**
 * Author：胡灯
 * Date：2021-12-05 0:27
 * Description：<描述>
 */
public enum EnumSingleton
{
    INSTANCE;
    private Object data;
    public static EnumSingleton getInstance()
    {
        return INSTANCE;
    }
    public Object getData()
    {
        return data;
    }
    public void setData(Object data)
    {
        this.data = data;
    }
}

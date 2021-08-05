package com.huawei.Daliy.threadLocal;
/**
 * Author：胡灯
 * Date：2021-08-04 23:00
 * Description：<描述>
 */
public class DataSourceContentHolder
{
    private static final ThreadLocal<Long> contextHolder = new ThreadLocal<>();

    // 设置id
    public static void setId(Long id) {
        contextHolder.set(id);
    }

    // 获取线程id
    public static Long getId() {
        return contextHolder.get();
    }

}

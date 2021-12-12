package com.huawei.pattern.proxy;
/**
 * @Author：胡灯
 * @Date：2021-12-12 10:30
 * @Description：DynamicDatasourceEntry
 */
public class DynamicDatasourceEntry
{
    // 默认数据源
    private static final String DEFAULT_SOURCE = null;
    private static ThreadLocal<String> local = new InheritableThreadLocal<>();
    private DynamicDatasourceEntry()
    {
    }
    // 清空数据源
    public static void clear()
    {
        local.remove();
    }
    public static String get()
    {
        return local.get();
    }
    // 还原数据源
    public static void restore()
    {
        local.set(DEFAULT_SOURCE);
    }
    // 设置已知名字的数据源
    public static void set(String dataSource)
    {
        local.set(dataSource);
    }
    // 根据年份动态设置数据源
    public static void set(int year)
    {
        local.set("DB_" + year);
    }
}

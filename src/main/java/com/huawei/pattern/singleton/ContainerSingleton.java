package com.huawei.pattern.singleton;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/**
 * Author：胡灯
 * Date：2021-12-06 22:17
 * Description：容器式单例
 */
public class ContainerSingleton
{
    private ContainerSingleton()
    {
    }
    private static Map<String, Object> ioc = new ConcurrentHashMap<>();
    public static Object getBean(String className)
    {
        synchronized (ioc)
        {
            if (!ioc.containsKey(className))
            {
                Object obj = null;
                try
                {
                    obj = Class.forName(className).newInstance();
                    ioc.put(className,obj);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                return obj;
            }else {
                return ioc.get(className);
            }
        }
    }
}

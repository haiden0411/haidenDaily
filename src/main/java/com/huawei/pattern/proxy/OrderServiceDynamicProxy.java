package com.huawei.pattern.proxy;
import io.swagger.models.auth.In;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * @Author：胡灯
 * @Date：2021-12-12 10:59
 * @Description：OrderServiceDynamicProxy
 */
public class OrderServiceDynamicProxy implements InvocationHandler
{
    private SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
    private Object target;
    public OrderServiceDynamicProxy(Object target)
    {
        this.target = target;
    }
    public Object getInstance()
    {
        Class<?> clazz = target.getClass();
        return Proxy.newProxyInstance(clazz.getClassLoader(),clazz.getInterfaces(),this);
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
    {
        before(args[0]);
        Object object = method.invoke(target, args);
        after();
        return object;
    }
    private void before(Object target)
    {
        try
        {
            System.out.println("proxy before method");
            Long time = (Long) target.getClass().getMethod("getCreateTime").invoke(target);
            Integer dbRouter = Integer.valueOf(yearFormat.format(new Date(time)));
            System.out.println("动态代理类自动分配到[db"+dbRouter+"]数据源处理数据");
            DynamicDatasourceEntry.set(dbRouter);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
    private void after()
    {
        System.out.println("proxy after method");
    }
}

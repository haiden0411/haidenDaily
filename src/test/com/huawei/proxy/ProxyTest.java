package com.huawei.proxy;
import com.huawei.pattern.proxy.*;
import org.aspectj.weaver.ast.Or;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * @Author：胡灯
 * @Date：2021-12-12 10:51
 * @Description：ProxyTest
 */
public class ProxyTest
{
    @Test
    public void testStaticRroxy() throws ParseException
    {
        // 静态代码
        Order order = new Order();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse("2021-12-12");
        order.setCreateTime(date.getTime());
        OrderServiceStaticProxy proxy = new OrderServiceStaticProxy(new OrderService());
        proxy.createOrder(order);
    }
    
    @Test
    public void testDynamicProxy() throws ParseException
    {
        Order order = new Order();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse("2022-12-12");
        order.setCreateTime(date.getTime());
        OrderServiceDynamicProxy dynamicProxy = new OrderServiceDynamicProxy(new OrderService());
        IOrderService orderService = (IOrderService) dynamicProxy.getInstance();
        orderService.createOrder(order);
    }


}

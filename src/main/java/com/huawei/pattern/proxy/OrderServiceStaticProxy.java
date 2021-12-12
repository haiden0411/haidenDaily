package com.huawei.pattern.proxy;
import io.swagger.models.auth.In;
import org.aspectj.weaver.ast.Or;

import java.beans.SimpleBeanInfo;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * @Author：胡灯
 * @Date：2021-12-12 10:39
 * @Description：OrderServiceStaticProxy
 */
public class OrderServiceStaticProxy
{
    private SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
    private IOrderService orderService;
    public OrderServiceStaticProxy(IOrderService orderService)
    {
        this.orderService = orderService;
    }
    public int createOrder(Order order)
    {
        before();
        Long createTime = order.getCreateTime();
        Integer dbRouter = Integer.valueOf(yearFormat.format(new Date(createTime)));
        System.out.println("静态代理类自动分配到[db"+dbRouter+"]数据源处理数据");
        DynamicDatasourceEntry.set(dbRouter);
        orderService.createOrder(order);
        after();
        return 0;
    }
    private void before()
    {
        System.out.println("proxy before method");
    }
    private void after()
    {
        System.out.println("proxy after method");
    }
}

package com.huawei.pattern.proxy;
/**
 * @Author：胡灯
 * @Date：2021-12-12 10:27
 * @Description：OrderService
 */
public class OrderService implements IOrderService
{
    private OrderDao orderDao;
    public OrderService()
    {
       orderDao = new OrderDao();
    }
    @Override
    public int createOrder(Order order)
    {
        System.out.println("OrderService调用orderDao创建订单");
        return orderDao.insert(order);
    }
}

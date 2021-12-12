package com.huawei.pattern.proxy;
/**
 * @Author：胡灯
 * @Date：2021-12-12 10:19
 * @Description：<描述>
 */
public class OrderDao
{
    public int insert(Order order){
        System.out.println("orderDao创建order成功");
        return 1;
    }
}

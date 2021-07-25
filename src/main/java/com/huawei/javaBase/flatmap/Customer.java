package com.huawei.javaBase.flatmap;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
/**
 * Author：胡灯
 * Date：2021-06-10 22:31
 * Description：<描述>
 */
@Data
public class Customer
{
    private String name;
    private List<Order> orders = new ArrayList<>();
    public Customer(String name) {
        this.name = name;
    }
    public String getName() { return name; }
    public List<Order> getOrders() { return orders; }
    public Customer addOrder(Order order) {
        orders.add(order);
        return this;
    }
}

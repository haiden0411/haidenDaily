package com.huawei.javaBase.flatmap;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
/**
 * Author：胡灯
 * Date：2021-06-10 22:32
 * Description：<描述>
 */
public class FlatMapTest
{
    public static void main(String[] args)
    {
        Customer sheridan = new Customer("Sheridan");
        Customer ivanova = new Customer("Ivanova");
        Customer garibaldi = new Customer("Garibaldi");
        sheridan.addOrder(new Order(1))
                .addOrder(new Order(2))
                .addOrder(new Order(3));
        ivanova.addOrder(new Order(4))
                .addOrder(new Order(5));
        List<Customer> customers = Arrays.asList(sheridan, ivanova, garibaldi);
        List<String> names = customers.stream().map(Customer::getName).collect(Collectors.toList());
        names.forEach(System.out::println);

        //将客户映射到订单
        customers.stream().map(customer -> customer.getOrders().stream()).forEach(System.out::println);
        List<Order> collect = customers.stream().flatMap(customer -> customer.getOrders().stream()).collect(Collectors.toList());
        collect.forEach(System.out::println);
    }
}

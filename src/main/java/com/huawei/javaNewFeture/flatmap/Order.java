package com.huawei.javaNewFeture.flatmap;
import lombok.Data;
/**
 * Author：胡灯
 * Date：2021-06-10 22:31
 * Description：<描述>
 */
@Data
public class Order
{
    private int id;
    public Order(int id) {
        this.id = id;
    }
    public int getId() { return id; }
}

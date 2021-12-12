package com.huawei.pattern.proxy;
import lombok.Data;
/**
 * @Author：胡灯
 * @Date：2021-12-12 10:19
 * @Description：<描述>
 */
@Data
public class Order
{
    private Object orderInfo;
    private Long createTime;
    private String id;
}

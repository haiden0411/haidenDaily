package com.huawei.springboot.domain;

/**
 * Author：胡灯
 * Date：2019-09-26 23:34
 * Description：<描述>
 */
public class Discount
{
    public enum Code{
        NONE(0),SILVER(5),GOLD(10),PLATNUM(15),DIAMOND(20);
        private final int percentage;
        Code(int percentage)
        {
            this.percentage = percentage;
        }
    }
}

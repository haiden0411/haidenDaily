package com.huawei.pattern.adapter;
/**
 * @Author：胡灯
 * @Date：2021-12-12 16:46
 * @Description：ILoginAdapter
 */
public interface ILoginAdapter
{
    boolean support(Object adapter);
    ResultMsg login(String id, Object adapter);
}

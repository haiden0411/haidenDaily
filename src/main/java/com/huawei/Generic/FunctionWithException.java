package com.huawei.Generic;
/**
 * Author：胡灯
 * Date：2021-05-22 23:51
 * Description：<描述>
 */
@FunctionalInterface
public interface FunctionWithException<T,R,E extends Exception>
{
    R apply(T t) throws E;
}

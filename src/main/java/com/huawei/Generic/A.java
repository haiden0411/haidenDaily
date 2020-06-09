package com.huawei.Generic;
/**
 * Author：胡灯
 * Date：2020-03-04 22:33
 * Description：<描述>
 */
public interface A<T extends A<T>>
{
    T add();
}

class B implements A<B>{
    @Override
    public B add()
    {
        return null;
    }
}
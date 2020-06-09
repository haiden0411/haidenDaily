package com.huawei.springboot.service;
import com.huawei.springboot.domain.User;
/**
 * Author：胡灯
 * Date：2020-06-02 23:41
 * Description：<描述>
 */
public interface UserValidator
{
    public boolean validate(User user);
}

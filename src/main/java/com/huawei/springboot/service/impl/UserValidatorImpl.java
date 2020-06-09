package com.huawei.springboot.service.impl;
import com.huawei.springboot.domain.User;
import com.huawei.springboot.service.UserValidator;
import org.aspectj.lang.annotation.DeclareParents;
/**
 * Author：胡灯
 * Date：2020-06-02 23:42
 * Description：<描述>
 */

public class UserValidatorImpl implements UserValidator
{
    @Override
    public boolean validate(User user)
    {
        System.out.println("引入新接口：" + UserValidator.class.getSimpleName());
        return user!=null;
    }
}

package com.huawei.springboot.service.impl;
import com.huawei.springboot.domain.User;
import com.huawei.springboot.service.UserService;
import org.springframework.stereotype.Service;
/**
 * Author：胡灯
 * Date：2020-06-02 22:51
 * Description：<描述>
 */
@Service
public class UserServiceImpl implements UserService
{
    @Override
    public void printUser(User user)
    {
        if (user == null)
        {
            throw new RuntimeException("检查用户参数为空");
        }
        System.out.println(user);
    }
}

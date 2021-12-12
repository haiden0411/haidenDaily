package com.huawei.pattern.adapter;
/**
 * @Author：胡灯
 * @Date：2021-12-12 16:22
 * @Description：老的登录应用
 */
public class PasswordService
{
    public ResultMsg regist(String username,String password)
    {
        return new ResultMsg(200,"注册成功",new Member());
    }

    public ResultMsg login(String username,String password)
    {
        System.out.println("usrname:"+username);
        return null;
    }
}

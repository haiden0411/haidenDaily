package com.huawei.pattern.adapter;
/**
 * @Author：胡灯
 * @Date：2021-12-12 16:47
 * @Description：AbstractAdapter
 */
public abstract class AbstractAdapter extends PasswordService implements ILoginAdapter
{
    public ResultMsg loginForRegist(String username,String password)
    {
        if (null==password)
        {
            password = "THIRD_EMPTY";
        }
        super.regist(username,password);
        return super.login(username,password);
    }
}

package com.huawei.pattern.adapter;
/**
 * @Author：胡灯
 * @Date：2021-12-12 16:48
 * @Description：LoginForQQAdapter
 */
public class LoginForWechat extends AbstractAdapter
{
    @Override
    public boolean support(Object adapter)
    {
        return adapter instanceof LoginForWechat;
    }
    @Override
    public ResultMsg login(String id, Object adapter)
    {
        if (!support(adapter))
        {
            return null;
        }
        return super.loginForRegist(id,null);
    }
}

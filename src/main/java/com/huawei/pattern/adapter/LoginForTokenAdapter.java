package com.huawei.pattern.adapter;
/**
 * @Author：胡灯
 * @Date：2021-12-12 17:44
 * @Description：LoginForTokenAdapter
 */
public class LoginForTokenAdapter extends AbstractAdapter
{
    @Override
    public boolean support(Object adapter)
    {
        return adapter instanceof LoginForTokenAdapter;
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

package com.huawei.pattern.adapter;
/**
 * @Author：胡灯
 * @Date：2021-12-12 17:41
 * @Description：LoginForTelAdaper
 */
public class LoginForTelAdapter extends AbstractAdapter
{
    @Override
    public boolean support(Object adapter)
    {
        return adapter instanceof LoginForTelAdapter;
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

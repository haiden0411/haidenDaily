package com.huawei.pattern.adapter;
/**
 * @Author：胡灯
 * @Date：2021-12-12 16:28
 * @Description：PassportForThirdAdapter
 */
public class PassportForThirdAdapter implements IPassportForThird
{
    @Override
    public ResultMsg loginForQQ(String openId)
    {
        return proccessLogin(openId,LoginForQQAdapter.class);
    }
    @Override
    public ResultMsg loginForWechat(String openId)
    {
        return proccessLogin(openId,LoginForWechat.class);
    }
    @Override
    public ResultMsg loginForToken(String openId)
    {
        return proccessLogin(openId,LoginForTokenAdapter.class);
    }
    @Override
    public ResultMsg loginForTelephone(String phone, String code)
    {
        return proccessLogin(phone,LoginForTelAdapter.class);
    }

    private ResultMsg proccessLogin(String id,Class<? extends ILoginAdapter> clazz)
    {
        try
        {
            ILoginAdapter adapter = clazz.newInstance();
            if (adapter.support(adapter))
            {
                return adapter.login(id,adapter);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}

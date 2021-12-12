package com.huawei.pattern.adapter;
/**
 * @Author：胡灯
 * @Date：2021-12-12 16:26
 * @Description：IPassportForThird
 */
public interface IPassportForThird
{
    ResultMsg loginForQQ(String openId);
    ResultMsg loginForWechat(String openId);
    ResultMsg loginForToken(String openId);
    ResultMsg loginForTelephone(String phone,String code);
}

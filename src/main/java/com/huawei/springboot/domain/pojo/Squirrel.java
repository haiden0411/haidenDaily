package com.huawei.springboot.domain.pojo;
import com.huawei.springboot.domain.pojo.define.Animal;
/**
 * Author：胡灯
 * Date：2020-06-02 21:22
 * Description：<描述>
 */
public class Squirrel implements Animal
{
    @Override
    public void use()
    {
        System.out.println("松鼠可以采果");
    }
}

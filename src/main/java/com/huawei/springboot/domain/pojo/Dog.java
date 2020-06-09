package com.huawei.springboot.domain.pojo;
import com.huawei.springboot.domain.pojo.define.Animal;
import org.springframework.stereotype.Component;
/**
 * Author：胡灯
 * Date：2020-06-02 1:07
 * Description：<描述>
 */
@Component
public class Dog implements Animal
{
    @Override
    public void use()
    {
        System.out.println("狗【"+Dog.class.getSimpleName()+"】是看门用的");
    }
}

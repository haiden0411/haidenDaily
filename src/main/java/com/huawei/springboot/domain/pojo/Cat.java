package com.huawei.springboot.domain.pojo;
import com.huawei.springboot.domain.pojo.define.Animal;
import org.springframework.stereotype.Component;
/**
 * Author：胡灯
 * Date：2020-06-02 1:13
 * Description：<描述>
 */
@Component
public class Cat implements Animal
{
    @Override
    public void use()
    {
        System.out.println("猫【"+Cat.class.getSimpleName()+"】是抓老鼠用的");
    }
}

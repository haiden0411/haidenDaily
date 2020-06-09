package com.huawei.springboot.domain.pojo;
import com.huawei.springboot.domain.pojo.define.Animal;
import com.huawei.springboot.domain.pojo.define.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
/**
 * Author：胡灯
 * Date：2020-06-02 1:06
 * Description：<描述>
 */
@Component
public class BussinessPerson implements Person
{

    private Animal animal;
    @Override
    public void service()
    {
        this.animal.use();
    }


    @Override
    @Autowired
    @Qualifier("squirrel")
    public void setAnimal(Animal animal)
    {
        System.out.println("延迟依赖注入");
        this.animal = animal;
    }

}

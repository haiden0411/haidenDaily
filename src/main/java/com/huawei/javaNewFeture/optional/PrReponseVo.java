package com.huawei.javaNewFeture.optional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.function.Consumer;
/**
 * Author：胡灯
 * Date：2021-07-24 23:05
 * Description：<描述>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrReponseVo
{
    private int id;
    private PrLineVo prLineVo;
    public static void main(String[] args)
    {
        Consumer<String> consumer = obj->{
            System.out.println(obj);
            System.out.println("调⽤用短信接⼝口发送短信，或者打印⽇日志");
        };

        sendMsg("88888",consumer);

    }

    public static void sendMsg(String phone,Consumer<String> consumer){
        consumer.accept(phone);
    }
}

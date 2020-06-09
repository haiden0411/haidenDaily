package com.huawei.springboot.controller;
import com.huawei.utils.JsonData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * Author：胡灯
 * Date：2020-05-31 20:49
 * Description：<描述>
 */
@RestController
@RequestMapping("/interceptor")
public class InterceptorController
{
    @GetMapping("/start")
    public JsonData start(){
        System.out.println("执行处理器逻辑");
        return JsonData.buildSuccess("成功");
    }
}

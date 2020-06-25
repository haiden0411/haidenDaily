package com.huawei.springboot.controller;
import com.huawei.springboot.domain.vo.SwaggerReqVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * Author：胡灯
 * Date：2020-06-25 17:04
 * Description：<描述>
 */
@RestController
@RequestMapping("/test")
public class SwaggerController
{
    @ApiOperation(value = "第一个swagger接口")
    @PostMapping("/swagger")
    public SwaggerReqVo testSwagger(@RequestBody SwaggerReqVo vo){
        return vo;
    }

}

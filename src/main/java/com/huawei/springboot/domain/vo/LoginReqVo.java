package com.huawei.springboot.domain.vo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * Author：胡灯
 * Date：2020-06-26 12:31
 * Description：<描述>
 */
@Data
public class LoginReqVo
{
    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "密码")
    private String password;
}

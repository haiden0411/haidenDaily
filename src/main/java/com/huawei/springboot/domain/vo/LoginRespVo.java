package com.huawei.springboot.domain.vo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * Author：胡灯
 * Date：2020-06-26 13:00
 * Description：<描述>
 */
@Data
public class LoginRespVo
{
    @ApiModelProperty(value = "用户凭证")
    private String token;
    @ApiModelProperty(value = "用户ID")
    private String userId;
}

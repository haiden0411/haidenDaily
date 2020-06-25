package com.huawei.springboot.domain.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * Author：胡灯
 * Date：2020-06-25 17:52
 * Description：<描述>
 */
@Data
@ApiModel(value = "com.huawei.springboot.domain.vo.RegisterReqVO",description = "注册用户信息")
public class RegisterReqVO
{
    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "用户密码")
    private String password;
    @ApiModelProperty(value = "性别")
    private Integer sex;
    @ApiModelProperty(value = "电话号")
    private String phone;
    @ApiModelProperty(value = "创建来源(1.web 2.android 3.ios")
    private Integer createWhere;
    @ApiModelProperty(value = "邮箱地址")
    private String email;
    @ApiModelProperty(value = "用户别名")
    private String nickName;
}

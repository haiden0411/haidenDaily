package com.huawei.springboot.domain.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * Author：胡灯
 * Date：2020-06-25 17:44
 * Description：<描述>
 */
@Data
@ApiModel(value = "com.huawei.springboot.doamin.vo.UpdateUserReqVO",description = "更新用户参数")
public class UpdateUserReqVO
{
    @ApiModelProperty(value = "用户id")
    private String id;
    @ApiModelProperty(value = "账号")
    private String username;
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "手机号")
    private String phone;
    @ApiModelProperty(value = "机构id")
    private String deptId;
    @ApiModelProperty(value = "真实名称")
    private String realName;
    @ApiModelProperty(value = "昵称")
    private String nickName;
    @ApiModelProperty(value = "邮箱")
    private String email;
    @ApiModelProperty(value = "状态")
    private Integer status;
    @ApiModelProperty(value = "性别")
    private Integer sex;
    @ApiModelProperty(value = "涞源")
    private Integer createWhere;
}

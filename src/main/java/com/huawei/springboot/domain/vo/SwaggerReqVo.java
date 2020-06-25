package com.huawei.springboot.domain.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * Author：胡灯
 * Date：2020-06-25 17:02
 * Description：<描述>
 */
@Data
@ApiModel(value = "com.huawei.springboot.doamin.vo",description = "用户请求资源")
public class SwaggerReqVo
{
    @ApiModelProperty(value = "姓名")
    private String name;
    @ApiModelProperty(value = "年龄")
    private Integer age;
    @ApiModelProperty(value = "电话号码")
    private String phone;
}

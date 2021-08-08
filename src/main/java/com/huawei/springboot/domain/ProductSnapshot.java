package com.huawei.springboot.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "product_snapshot")
public class ProductSnapshot {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @TableField(value = "name")
    private String name;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    public ProductSnapshot(String name, Date createTime) {
        this.name = name;
        this.createTime = createTime;
    }
}

package com.huawei.springboot.domain;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.jfinal.template.expr.ast.Id;
import lombok.Data;

/**
 * product
 * @author 
 */
@Data
@TableName("product")
public class Product implements Serializable {


    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /**
     * 产品名
     */
    @TableField(value = "name")
    private String name;

    /**
     * 产品编号
     */
    @TableField(value = "product_no")
    private String productNo;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;


}
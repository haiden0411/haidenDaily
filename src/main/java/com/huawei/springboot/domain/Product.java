package com.huawei.springboot.domain;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * product
 * @author 
 */
@Data
public class Product implements Serializable {
    private Integer id;


    private static final long serialVersionUID = 1L;
    /**
     * 产品名
     */
    private String name;

    /**
     * 产品编号
     */
    private String productNo;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


}
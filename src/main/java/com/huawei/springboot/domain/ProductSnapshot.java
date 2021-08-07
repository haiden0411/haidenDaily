package com.huawei.springboot.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductSnapshot {
    private Integer id;
    private String name;
    private Date createTime;
    public ProductSnapshot(String name, Date createTime) {
        this.name = name;
        this.createTime = createTime;
    }
}

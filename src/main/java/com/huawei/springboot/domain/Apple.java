package com.huawei.springboot.domain;
import com.huawei.reflect.Column;
import com.huawei.reflect.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Table(name = "t_apple",value = "aaaaaa")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Apple
{
    @Column(value = "t_field")
    private String color;
    private Integer weight;


}

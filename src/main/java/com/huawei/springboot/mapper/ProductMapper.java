package com.huawei.springboot.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huawei.springboot.domain.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductMapper extends BaseMapper<Product>
{

    List<Product> findAll();
    void insertBatch(@Param("products") List<Product> products);
}
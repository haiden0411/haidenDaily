package com.huawei.springboot.mapper;
import com.huawei.springboot.domain.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductMapper
{
    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    List<Product> selectByIds(@Param("ids") List<Integer> ids);

    List<Product> findAll();

    void insertBatch(@Param("products") List<Product> products);
}
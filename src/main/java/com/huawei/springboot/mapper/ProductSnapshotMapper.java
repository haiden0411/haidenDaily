package com.huawei.springboot.mapper;
import com.huawei.springboot.domain.ProductSnapshot;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductSnapshotMapper
{
    void insertBatch(@Param("snapshots") List<ProductSnapshot> products);
}
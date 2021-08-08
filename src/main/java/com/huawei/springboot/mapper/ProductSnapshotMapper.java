package com.huawei.springboot.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huawei.springboot.domain.ProductSnapshot;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductSnapshotMapper extends BaseMapper<ProductSnapshot>
{
    void insertBatch(@Param("snapshots") List<ProductSnapshot> products);
}
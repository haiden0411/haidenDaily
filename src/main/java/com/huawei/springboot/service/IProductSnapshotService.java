package com.huawei.springboot.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huawei.springboot.domain.Product;
import com.huawei.springboot.domain.ProductSnapshot;

import java.util.List;

/**
 * Author：胡灯
 * Date：2021-08-07 11:44
 * Description：<描述>
 */

public interface IProductSnapshotService extends IService<ProductSnapshot>
{
    void batchInsert(List<ProductSnapshot> snapshots) throws Exception;
    List<ProductSnapshot> assembleProductSnapshots(List<Product> products);
}

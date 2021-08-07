package com.huawei.springboot.service.impl;

import com.huawei.springboot.domain.Product;
import com.huawei.springboot.domain.ProductSnapshot;
import com.huawei.springboot.mapper.ProductMapper;
import com.huawei.springboot.mapper.ProductSnapshotMapper;
import com.huawei.springboot.service.IProductSnapshotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductSnapshotService implements IProductSnapshotService {
    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductSnapshotMapper productSnapshotMapper;

    @Override
    public void batchInsert(List<ProductSnapshot> snapshots) throws Exception {
        productSnapshotMapper.insertBatch(snapshots);
    }

    @Override
    public List<ProductSnapshot> assembleProductSnapshots(List<Product> products) {
        return products
                .stream()
                .map(product -> new ProductSnapshot(product.getName(),product.getCreateTime()))
                .collect(Collectors.toList());
    }
}

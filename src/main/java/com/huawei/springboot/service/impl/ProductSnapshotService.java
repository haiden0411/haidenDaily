package com.huawei.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huawei.springboot.domain.Product;
import com.huawei.springboot.domain.ProductSnapshot;
import com.huawei.springboot.mapper.ProductMapper;
import com.huawei.springboot.mapper.ProductSnapshotMapper;
import com.huawei.springboot.service.IProductSnapshotService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

@Service
public class ProductSnapshotService extends ServiceImpl<ProductSnapshotMapper,ProductSnapshot> implements IProductSnapshotService {
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
    @Override
    @Transactional
    public Integer saveSnapshot(ProductSnapshot snapshot)
    {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> saveSnapshotAysnc(snapshot));
        return 1;
    }

    // 在线程中，事务是不能回滚的
    @Transactional
    public Integer saveSnapshotAysnc(ProductSnapshot snapshot)
    {
        int count = productSnapshotMapper.insert(snapshot);
        System.out.println(1/0);
        return count;
    }
}

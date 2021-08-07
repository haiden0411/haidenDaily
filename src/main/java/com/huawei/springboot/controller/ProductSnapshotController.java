package com.huawei.springboot.controller;

import com.huawei.springboot.domain.Product;
import com.huawei.springboot.domain.ProductSnapshot;
import com.huawei.springboot.service.IProductService;
import com.huawei.springboot.service.IProductSnapshotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("snapshot")
public class ProductSnapshotController {
    @Autowired
    private IProductSnapshotService productSnapshotService;

    @Autowired
    private IProductService productService;


    @RequestMapping("create")
    public String createProductSnapshot() throws Exception {
        List<Product> products = productService.findAll();
        List<ProductSnapshot> productSnapshots = productSnapshotService.assembleProductSnapshots(products);
        productSnapshotService.batchInsert(productSnapshots);
        return "success";
    }
}

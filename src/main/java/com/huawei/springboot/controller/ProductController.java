package com.huawei.springboot.controller;
import com.huawei.springboot.domain.Product;
import com.huawei.springboot.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
/**
 * Author：胡灯
 * Date：2021-08-07 11:35
 * Description：<描述>
 */
@RestController
@RequestMapping("product")
public class ProductController
{

    @Autowired
    private IProductService productService;

    @RequestMapping("/findbyId/{id}")
    public Product queryProductById(@PathVariable("id") Integer id)
    {
        return productService.findProductById(id);
    }

    @RequestMapping("/findbyIds")
    public List<Product> findProducts(List<Integer> ids)
    {
        return productService.findProductByIds(ids);
    }

    @RequestMapping("/batchInser/{size}")
    public void batchInser(@PathVariable("size") int size) throws Exception
    {
        productService.batchInsert(size);
    }




}

package com.huawei.springboot.controller;
import com.google.common.base.Stopwatch;
import com.huawei.springboot.domain.Product;
import com.huawei.springboot.service.IProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;
/**
 * Author：胡灯
 * Date：2021-08-07 11:35
 * Description：<描述>
 */
@RestController
@RequestMapping("product")
@Slf4j
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

    @RequestMapping("/batchInsert/{size}")
    public String batchInser(@PathVariable("size") int size) throws Exception
    {
        productService.batchInsert(size);
        return "success";
    }

    @RequestMapping("/findAll")
    public String findAll() throws Exception
    {
        List<Product> products = productService.findAll();
        return "success";
    }






}

package com.huawei.springboot.service.impl;
import cn.hutool.core.lang.Snowflake;
import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;
import com.huawei.springboot.domain.Product;
import com.huawei.springboot.mapper.ProductMapper;
import com.huawei.springboot.service.IProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
/**
 * Author：胡灯
 * Date：2021-08-07 11:48
 * Description：<描述>
 */
@Service
@Slf4j
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService
{
    private static final BlockingQueue BLOCKING_QUEUE = new ArrayBlockingQueue(8);
    private static final ThreadPoolExecutor.CallerRunsPolicy POLICY = new ThreadPoolExecutor.CallerRunsPolicy();
    private static final ThreadPoolExecutor EXECUTOR = new ThreadPoolExecutor(4, 8, 60, TimeUnit.SECONDS, BLOCKING_QUEUE, POLICY);
    @Autowired
    private ProductMapper mapper;
    private static final Integer batchSize = 50000;
    //雪花算法
    private static final Snowflake SNOWFLAKE = new Snowflake(2, 3);
    @Override
    public Product findProductById(Integer id)
    {
        return mapper.selectById(id);
    }
    @Override
    public List<Product> findProductByIds(List<Integer> ids)
    {
        return mapper.selectBatchIds(ids);
    }
    @Override
    @Transactional(timeout = 1)
    public void batchInsert(int times) throws Exception
    {
        List<ProductCallable> callables = new ArrayList<>();
        for (int i = 0; i < times; i++)
        {
            callables.add(new ProductCallable(batchSize));
        }
        EXECUTOR.invokeAll(callables);
    }
    /**
     *
     *  总体思路是把整个list分成50w一份，异步执行
     */
    @Override
    @Transactional(timeout = 16)
    public List<Product> findAll()
    {
        Stopwatch startedId = Stopwatch.createStarted();
        QueryWrapper<Product> wrapperId = new QueryWrapper<>();;
        wrapperId.select("id");
        List<Product> products1 = mapper.selectList(wrapperId);
        log.info("selectID,take time:{}",startedId.elapsed(TimeUnit.MILLISECONDS));
        log.info("count:"+products1.size());
        List<Integer> ids = products1.stream().map(Product::getId).collect(Collectors.toList());
        List<List<Integer>> lists = Lists.partition(ids, 500000);
        List<Product> productList = new ArrayList<>();
        List<CompletableFuture<List<Product>>> productFutures = new ArrayList<>();
        for (List<Integer> list : lists)
        {
            CompletableFuture<List<Product>> future = CompletableFuture.supplyAsync(() -> assembleProducts(list));
            productFutures.add(future);

        }
        List<Product> collect = productFutures.stream().map(CompletableFuture::join).flatMap(Collection::stream).collect(Collectors.toList());
        log.info("final count:"+collect.size());
        return productList;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Product> assembleProducts(List<Integer> ids)
    {
       return mapper.selectBatchIds(ids);
    }

    class ProductCallable implements Callable<Boolean>
    {
        private Integer batchSize;
        public ProductCallable(Integer batchSize)
        {
            this.batchSize = batchSize;
        }
        @Override
        public Boolean call() throws Exception
        {
            List<Product> products = new ArrayList<>();
            for (Integer i = 0; i < batchSize; i++)
            {
                Product product = new Product();
                product.setName("product_" + SNOWFLAKE.nextIdStr());
                product.setProductNo("product_no_" + SNOWFLAKE.nextIdStr());
                product.setCreateTime(new Date());
                product.setUpdateTime(new Date());
                products.add(product);
            }
            Stopwatch started = Stopwatch.createStarted();
            log.info("begin batch insert data:{}", products.size());
            mapper.insertBatch(products);
            long elapsed = started.elapsed(TimeUnit.MILLISECONDS);
            log.info(Thread.currentThread().getName() + " batchInsert elapsed:{}ms", elapsed);
            return true;
        }
    }
}

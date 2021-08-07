package com.huawei.springboot.service.impl;
import cn.hutool.core.lang.Snowflake;
import com.huawei.springboot.domain.Product;
import com.huawei.springboot.mapper.ProductMapper;
import com.huawei.springboot.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;
/**
 * Author：胡灯
 * Date：2021-08-07 11:48
 * Description：<描述>
 */
@Service
public class ProductServiceImpl implements IProductService
{

    private static final BlockingQueue BLOCKING_QUEUE=new ArrayBlockingQueue(8);

    private static final ThreadPoolExecutor.CallerRunsPolicy POLICY=new ThreadPoolExecutor.CallerRunsPolicy();

    private static final ThreadPoolExecutor EXECUTOR=new ThreadPoolExecutor(4,8,60,TimeUnit.SECONDS,BLOCKING_QUEUE,POLICY);
    @Autowired
    private ProductMapper mapper;

    private static final Integer batchSize = 50000;

    //雪花算法
    private static final Snowflake SNOWFLAKE=new Snowflake(2,3);

    @Override
    public Product findProductById(Integer id)
    {
        return mapper.selectByPrimaryKey(id);
    }
    @Override
    public List<Product> findProductByIds(List<Integer> ids)
    {
        return mapper.selectAll(ids);
    }

    @Override
    public void batchInsert(int size) throws Exception
    {

        List<ProductCallable> callables = new ArrayList<>();
        for (int i = 0; i < size; i++)
        {
            callables.add(new ProductCallable(batchSize));
        }
        EXECUTOR.invokeAll(callables);
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
                product.setName("product_"+SNOWFLAKE.nextIdStr());
                product.setProductNo("product_no_"+SNOWFLAKE.nextIdStr());
                product.setCreateTime(new Date());
                product.setUpdateTime(new Date());
                products.add(product);
            }
            mapper.insertBatch(products);
            return true;
        }
    }

}

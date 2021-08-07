package com.huawei.springboot.service;
import com.huawei.springboot.domain.Product;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * Author：胡灯
 * Date：2021-08-07 11:44
 * Description：<描述>
 */

public interface IProductService
{
    Product findProductById(Integer id);
    List<Product> findProductByIds(List<Integer> ids);
    void batchInsert(int size) throws InterruptedException, Exception;
}

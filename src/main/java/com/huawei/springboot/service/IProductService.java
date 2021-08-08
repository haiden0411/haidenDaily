package com.huawei.springboot.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huawei.springboot.domain.Product;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * Author：胡灯
 * Date：2021-08-07 11:44
 * Description：<描述>
 */

public interface IProductService  extends IService<Product>
{
    Product findProductById(Integer id);
    List<Product> findProductByIds(List<Integer> ids);
    void batchInsert(int times) throws Exception;
    List<Product> findAll();

}

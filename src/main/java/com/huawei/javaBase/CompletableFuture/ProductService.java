package com.huawei.javaBase.CompletableFuture;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;
/**
 * Author：胡灯
 * Date：2021-05-23 16:35
 * Description：<描述>
 */
public class ProductService
{
    private Map<Integer, Product> cache = new HashMap<>();
    private Logger logger = Logger.getLogger(ProductService.class.getName());
    private Product getLocal(int id)
    {
        return cache.get(id);
    }
    private Product getRemote(int id)
    {
        try
        {
            Thread.sleep(1000);
            if (id == 666)
            {
                throw new RuntimeException("evil request");
            }
        }
        catch (InterruptedException e)
        {
        }
        return new Product(id, "name");
    }
    public CompletableFuture<Product> getProduct(int id)
    {
        try
        {
            Product local = getLocal(id);
            if (local != null)
            {
                return CompletableFuture.completedFuture(local);
            }else
            {
                CompletableFuture<Product> future = new CompletableFuture<>();
                Product remote = getRemote(id);
                cache.put(id, remote);
                future.complete(remote);
                return future;
            }
        }
        catch (Exception e)
        {
            CompletableFuture<Product> future = new CompletableFuture<>();
            future.completeExceptionally(e);
            return future;
        }
    }

    public CompletableFuture<Product> getProductSync(int id)
    {
        try
        {
            Product local = getLocal(id);

            if (local != null)
            {
                logger.info("getLocal with id=" + id);
                return CompletableFuture.completedFuture(local);
            }else
            {
                logger.info("getLocal with id=" + id);
                CompletableFuture<Product> future = CompletableFuture.supplyAsync(() ->
                {
                    Product remote = getRemote(id);
                    cache.put(id, remote);
                    return remote;
                });
                return future;
            }
        }
        catch (Exception e)
        {
            CompletableFuture<Product> future = new CompletableFuture<>();
            future.completeExceptionally(e);
            return future;
        }
    }

}

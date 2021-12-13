package com.huawei.daily;
import com.huawei.javaNewFeture.CompletableFuture.ProductService;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;
/**
 * Author：胡灯
 * Date：2021-05-23 16:44
 * Description：<描述>
 */
public class TestProductService
{
    private ProductService demo = null;
    @Before
    public void setUp()
    {
        demo = new ProductService();
    }
    @Test(expected = ExecutionException.class)
    public void testException() throws Exception
    {
        demo.getProduct(666).get();
    }
    @Test
    public void testExceptionWithCause()
    {
        try
        {
            demo.getProduct(666).get();
            fail("Houson,we have a problem");
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        catch (ExecutionException e)
        {
            assertEquals(ExecutionException.class,e.getClass());
            assertEquals(RuntimeException.class,e.getCause().getClass());
        }
    }
}

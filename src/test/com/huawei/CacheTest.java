package huawei;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import org.junit.Test;

import java.util.concurrent.TimeUnit;
/**
 * Author：胡灯
 * Date：2021-01-30 23:39
 * Description：<描述>
 */
public class CacheTest
{

    @Test
    public void testExpire() throws InterruptedException
    {
        Cache<String,String> cache = CacheBuilder
                .newBuilder()
                .maximumSize(2)
                .expireAfterWrite(3, TimeUnit.SECONDS)
                .build();
        cache.put("k1","value1");
        int time = 1;
        while (true)
        {
            System.out.println("第" + time++ + "次取到的key值:" + cache.getIfPresent("k1"));
            Thread.sleep(1000);
            if (time>10)
            {
                break;
            }
        }
    }
    @Test
    public void testWeakRef()
    {
        Cache<String,Object> cache = CacheBuilder
                .newBuilder()
                .maximumSize(2)
                .weakValues()
                .build();
        Object o = new Object();
        cache.put("k1",o);
        o = new Object();
        System.gc();
        System.out.println(cache.getIfPresent("key1"));
    }
    @Test
    public void testRemoveLister()
    {
        RemovalListener<String,String> listener = new RemovalListener<String, String>()
        {
            @Override
            public void onRemoval(RemovalNotification<String, String> notification)
            {
                System.out.println("[" + notification.getKey() + ":" + notification.getValue() + "] is removed");
            }
        };
        Cache<String, String> cache = CacheBuilder.newBuilder()
                .maximumSize(3)
                .removalListener(listener)
                .build();
        Object value = new Object();
        cache.put("k1","v1");
        cache.put("k2","v1");
        cache.put("k3","v1");
        cache.put("k4","v1");
        cache.put("k5","v1");
        cache.put("k6","v1");
        cache.put("k7","v1");
        cache.put("k8","v1");
    }
}

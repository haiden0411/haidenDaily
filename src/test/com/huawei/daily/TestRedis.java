package com.huawei.daily;
import com.huawei.Daily_App;
import com.huawei.springboot.service.JedisService;
import com.huawei.springboot.service.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
/**
 * Author：胡灯
 * Date：2020-05-29 21:57
 * Description：<描述>
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Daily_App.class)
public class TestRedis
{
    @Autowired
    private JedisService jedisService;

    @Autowired
    private RedisService redisService;

    @Test
    public void testRedis1(){
        //System.out.println(redisService.exists("name"));
        //redisService.set("name","haiden",300);
        System.out.println(jedisService.get("aa"));
    }

    @Test
    public void testRedisTemplate(){
        System.out.println(redisService.get("aa"));
        redisService.set("name","haiden");
    }


}

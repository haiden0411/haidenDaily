package com.huawei.springboot.listener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

import javax.print.DocFlavor;
import java.nio.charset.StandardCharsets;
/**
 * Author：胡灯
 * Date：2020-10-11 8:20
 * Description：<描述>
 */
@Slf4j
public class KeyExpireLisener extends KeyExpirationEventMessageListener
{
    public KeyExpireLisener(RedisMessageListenerContainer listenerContainer)
    {
        super(listenerContainer);
    }
    @Override
    public void onMessage(Message message, byte[] pattern)
    {
        String channel = new String(message.getChannel(), StandardCharsets.UTF_8);
        String key = new String(message.getBody(), StandardCharsets.UTF_8);
        log.info("redis key 过期，pattern={},channer={},key={}",new String(pattern),channel,key);
    }
}

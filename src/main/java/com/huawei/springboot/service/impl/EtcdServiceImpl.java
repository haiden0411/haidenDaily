package com.huawei.springboot.service.impl;/**
 * Author：胡灯
 * Date：2021-11-21 11:41
 * Description：<描述>
 */
import com.huawei.springboot.service.EtcdService;
import io.etcd.jetcd.ByteSequence;
import io.etcd.jetcd.Client;
import io.etcd.jetcd.KV;
import io.etcd.jetcd.Response;
import io.etcd.jetcd.kv.GetResponse;
import io.etcd.jetcd.options.DeleteOption;
import io.etcd.jetcd.options.GetOption;

import static com.google.common.base.Charsets.UTF_8;
/**
 * @author Administrator
 * @description: EtcdService
 * @date 2021/11/21 11:41
 */
public class EtcdServiceImpl implements EtcdService
{
    private Client client;
    private final String endpoints;
    private final Object lock = new Object();
    public EtcdServiceImpl(String endpoints)
    {
        super();
        this.endpoints = endpoints;
    }
    /**
     * 将字符串转为客户端所需的ByteSequence实例
     *
     * @param val
     * @return
     */
    public static ByteSequence bytesOf(String val)
    {
        return ByteSequence.from(val, UTF_8);
    }
    /**
     * 新建key-value客户端实例
     *
     * @return
     */
    private KV getKVClient()
    {
        if (null == client)
        {
            synchronized (lock)
            {
                if (null == client)
                {
                    client = Client.builder().endpoints(endpoints.split(",")).build();
                }
            }
        }
        return client.getKVClient();
    }
    @Override
    public void close()
    {
        client.close();
        client = null;
    }
    @Override
    public Response.Header put(String key, String value) throws Exception
    {
        return getKVClient().put(bytesOf(key), bytesOf(value)).get().getHeader();
    }
    @Override
    public String getSingle(String key) throws Exception
    {
        GetResponse getResponse = getKVClient().get(bytesOf(key)).get();
        getResponse.getKvs().forEach(keyValue ->
        {
            System.out.println("getCreateRevision:" + keyValue.getCreateRevision());
            System.out.println("getModRevision:" + keyValue.getModRevision());
            System.out.println("getLease:" + keyValue.getLease());
            System.out.println("getVersion:" + keyValue.getVersion());
        });
        return getResponse.getCount() > 0 ?
                getResponse.getKvs().get(0).getValue().toString(UTF_8) :
                null;
    }
    @Override
    public GetResponse getRange(String key, GetOption getOption) throws Exception
    {
        return getKVClient().get(bytesOf(key), getOption).get();
    }
    @Override
    public long deleteSingle(String key) throws Exception
    {
        return getKVClient().delete(bytesOf(key)).get().getDeleted();
    }
    @Override
    public long deleteRange(String key, DeleteOption deleteOption) throws Exception
    {
        return getKVClient().delete(bytesOf(key), deleteOption).get().getDeleted();
    }
}

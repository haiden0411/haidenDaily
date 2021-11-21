package com.huawei.springboot.service;/**
 * Author：胡灯
 * Date：2021-11-21 11:40
 * Description：<描述>
 */
import io.etcd.jetcd.Response;
import io.etcd.jetcd.kv.GetResponse;
import io.etcd.jetcd.options.DeleteOption;
import io.etcd.jetcd.options.GetOption;
/**
 * @description: IEtcdService
 * @author Administrator
 * @date 2021/11/21 11:40
 */
public interface EtcdService
{
    /**
     * 写入
     * @param key
     * @param value
     */
    Response.Header put(String key, String value) throws Exception;

    /**
     * 读取
     * @param key
     * @return
     */
    String getSingle(String key) throws Exception;


    /**
     * 带额外条件的查询操作，例如前缀、结果排序等
     * @param key
     * @param getOption
     * @return
     */
    GetResponse getRange(String key, GetOption getOption) throws Exception;

    /**
     * 单个删除
     * @param key
     * @return
     */
    long deleteSingle(String key) throws Exception;

    /**
     * 范围删除
     * @param key
     * @param deleteOption
     * @return
     */
    long deleteRange(String key, DeleteOption deleteOption) throws Exception;

    /**
     * 关闭，释放资源
     */
    void close();
}

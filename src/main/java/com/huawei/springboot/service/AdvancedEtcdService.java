package com.huawei.springboot.service;/**
 * Author：胡灯
 * Date：2021-11-21 16:24
 * Description：<描述>
 */
import io.etcd.jetcd.Watch;
/**
 * @description: AdvancedEtcdService
 * @author Administrator
 * @date 2021/11/21 16:24
 */
public interface AdvancedEtcdService
{
    /**
     * 乐观锁，指定key的当前值如果等于expectValue，就设置成updateValue
     * @param key           键
     * @param expectValue   期望值
     * @param updateValue   达到期望值时要设置的值
     */
    boolean cas(String key, String expectValue, String updateValue) throws Exception;

    /**
     * 关闭，释放资源
     */
    void close();

    /**
     * 为指定key添加监听
     * @param key       键
     * @param listener  监听事件
     * @return          jetcd对应的监听对象
     * @throws Exception
     */
    Watch.Watcher watch(String key, Watch.Listener listener) throws Exception;

    /**
     * 带无限续租的写操作
     * @param key   键
     * @param value 值
     * @throws Exception
     */
    void putWithLease(String key, String value) throws Exception;

}

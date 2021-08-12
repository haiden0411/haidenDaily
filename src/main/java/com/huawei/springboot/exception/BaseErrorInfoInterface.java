package com.huawei.springboot.exception;

/**
 * @author haiden
 * @title: BaseErrorInfoInterface
 * @projectName 2021-start
 * @description: 异常接口
 * @date 2021/8/12 22:05
 */
public interface BaseErrorInfoInterface {

    /**
     * 异常码
     * @author haiden
     * @date 2021/8/12 22:28
     * @return String
     */
    String getResultCode();

    /**
     * 异常信息
     * @author haiden
     * @date 2021/8/12 22:36
     * @return String
     */
    String getResultMsg();
}

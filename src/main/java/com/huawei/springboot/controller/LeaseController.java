package com.huawei.springboot.controller;
/**
 * Author：胡灯
 * Date：2021-11-21 17:28
 * Description：<描述>
 */
import com.huawei.springboot.service.AdvancedEtcdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
/**
 * @description: LeaseController
 * @author Administrator
 * @date 2021/11/21 17:28
 */
@RestController
public class LeaseController
{
    @Autowired
    AdvancedEtcdService advancedEtcdService;

    @RequestMapping(value = "/lease/{key}/{value}", method = RequestMethod.GET)
    public String lease(@PathVariable("key") String key, @PathVariable("value") String value) throws Exception {
        advancedEtcdService.putWithLease(key, value);
        return "lease success " + new Date();
    }

}

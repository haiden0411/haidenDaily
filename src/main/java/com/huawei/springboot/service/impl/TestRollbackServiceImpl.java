package com.huawei.springboot.service.impl;
/**
 * Author：胡灯
 * Date：2021-10-16 16:23
 * Description：测试事务回滚
 */
import com.huawei.springboot.service.TestRollbackService;
import com.huawei.springboot.service.TestTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
/**
 * @description: TestRollbackServiceImpl
 * @author Administrator
 * @date 2021/10/16 16:23
 */
@Service
public class TestRollbackServiceImpl implements TestRollbackService
{
    @Resource
    private TestTransactionService testTransactionService;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void test1()
    {
        try
        {
            testTransactionService.test2();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
    }
}

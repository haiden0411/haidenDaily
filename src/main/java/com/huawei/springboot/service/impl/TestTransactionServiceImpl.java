package com.huawei.springboot.service.impl;
/**
 * Author：胡灯
 * Date：2021-10-16 16:22
 * Description：测试事务回滚
 */
import com.huawei.springboot.service.TestRollbackService;
import com.huawei.springboot.service.TestTransactionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * @description: TestTransactionServiceImpl
 * @author Administrator
 * @date 2021/10/16 16:22
 */
@Service
public class TestTransactionServiceImpl implements TestTransactionService
{
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void test2()
    {
        throw new RuntimeException("test rollback");
    }
}

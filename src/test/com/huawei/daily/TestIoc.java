package com.huawei.daily;
import com.huawei.Daily_App;
import com.huawei.springboot.config.ParamsBean;
import com.huawei.springboot.domain.pojo.BussinessPerson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.Executor;

import static org.junit.Assert.*;
/**
 * Author：胡灯
 * Date：2020-06-02 1:15
 * Description：<描述>
 */
@SpringBootTest(classes = Daily_App.class)
public class TestIoc
{

    @Test
    public void testGetBean(){
        AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(Daily_App.class);
        BussinessPerson bean = app.getBean(BussinessPerson.class);
        bean.service();
    }

    @Test
    public void testXmlParams(){
        AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(Daily_App.class);
        ParamsBean paramsBean = app.getBean(ParamsBean.class);
        System.out.println("url:"+paramsBean.getJdbcUrl());
        System.out.println("password:"+paramsBean.getPassword());
        System.out.println("user:"+paramsBean.getUsername());
    }

    @Test
    public void testScope(){
        AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(Daily_App.class);
        ParamsBean paramsBean1 = app.getBean(ParamsBean.class);
        ParamsBean paramsBean2 = app.getBean(ParamsBean.class);
        assertSame(paramsBean1,paramsBean2);
    }

    @Test
    public void testExcecuotr(){
        AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(Daily_App.class);
        ThreadPoolTaskExecutor myTaskExecutor = (ThreadPoolTaskExecutor) app.getBean("myTaskExecutor");
        myTaskExecutor.execute(() -> {
            System.out.println("aa");
        });

    }

}

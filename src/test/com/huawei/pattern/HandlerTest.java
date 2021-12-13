package com.huawei.pattern;
import com.huawei.pattern.chain.ConcreteHandlerA;
import com.huawei.pattern.chain.ConcreteHandlerB;
import com.huawei.pattern.chain.Handler;
import org.junit.Test;
/**
 * @Author：胡灯
 * @Date：2021-12-13 21:46
 * @Description：HandlerTest
 */
public class HandlerTest
{
    @Test
    public void testHandler1(){
        Handler handlerA = new ConcreteHandlerA();
        Handler handlerB = new ConcreteHandlerB();
        handlerA.setNextHandler(handlerB);
        handlerA.handleRequest("requestB");
    }
}

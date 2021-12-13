package com.huawei.pattern;
import com.huawei.pattern.state.ConcreteStateA;
import com.huawei.pattern.state.ConcreteStateB;
import com.huawei.pattern.state.Context;
import com.huawei.pattern.state.IState;
import org.junit.Test;
/**
 * @Author：胡灯
 * @Date：2021-12-13 22:47
 * @Description：StateTest
 */
public class StateTest
{
    @Test
    public void testState1(){
        IState stateA = new ConcreteStateA();
        IState stateB = new ConcreteStateB();
        Context context = new Context();
        context.setState(stateB);
        context.handle();
    }
}

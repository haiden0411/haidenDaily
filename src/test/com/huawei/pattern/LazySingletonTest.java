package com.huawei.pattern;
import com.huawei.pattern.singleton.EnumSingleton;
import com.huawei.pattern.singleton.ExectorThread;
import com.huawei.pattern.singleton.LazyDoubleCheckSingleton;
import com.huawei.pattern.singleton.LazyStaticInnerClassSingleton;
import org.aspectj.weaver.ast.Var;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.lang.reflect.Constructor;
/**
 * Author：胡灯
 * Date：2021-12-04 23:16
 * Description：<描述>
 */
public class LazySingletonTest
{

    // 还原破坏单例现场
    @Test
    public void testReflection(){
        try
        {
            Class<?> clazz = EnumSingleton.class;
            Constructor<?> c = clazz.getDeclaredConstructor(null);
            c.setAccessible(true);
            Object o1 = c.newInstance();
            Object o2 = c.newInstance();
            System.out.println(o1 == o2);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void testEnumSingleton() throws Exception
    {
        EnumSingleton instance1 = null;
        EnumSingleton instance2 = EnumSingleton.getInstance();
        instance2.setData(new Object());
        FileOutputStream fos = new FileOutputStream("EnumSingleton.obj");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(instance2);
        oos.flush();
        oos.close();
        FileInputStream fis = new FileInputStream("EnumSingleton.obj");
        ObjectInputStream ois = new ObjectInputStream(fis);
        instance1 = (EnumSingleton) ois.readObject();
        ois.close();
        System.out.println(instance1.getData());
        System.out.println(instance2.getData());
        System.out.println(instance1==instance2);
    }

}

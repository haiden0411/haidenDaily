package com.huawei.reflect;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
/**
 * @Author：胡灯
 * @Date：2021-12-14 21:40
 * @Description：ReflectTest
 */
public class ReflectTest
{
    @Test
    public void testConstrctor() throws ClassNotFoundException
    {
        Class<?> classInfo = Class.forName("com.huawei.reflect.A");
        Constructor<?>[] constructors = classInfo.getConstructors();
        // getConstructors
        for (Constructor<?> constructor : constructors)
        {
            System.out.println(constructor.toString());
        }
        System.out.println("<<<==========================================>>>");
        // DeclaredConstructors
        Constructor<?>[] declaredConstructors = classInfo.getDeclaredConstructors();
        for (Constructor<?> declaredConstructor : declaredConstructors)
        {
            System.out.println(declaredConstructor.toString());
        }
    }

    @Test
    public void testGetMethod() throws ClassNotFoundException
    {
        Class<?> classInfo = Class.forName("com.huawei.reflect.A");
        Method[] methods = classInfo.getMethods();
        for (Method method : methods)
        {
            System.out.println(method.toString());
        }
        System.out.println("<<<==========================================>>>");
        Method[] declaredMethods = classInfo.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods)
        {
            System.out.println(declaredMethod.toString());
        }
    }

    @Test
    public void testNewInstance() throws Exception
    {
        Class<?> classInfo = Class.forName("com.huawei.reflect.A");
        Object obj = classInfo.getConstructor().newInstance();
        Method method = classInfo.getMethod("func1");
        method.invoke(obj);
        Method func2 = classInfo.getMethod("func2", Integer.class);
        func2.invoke(obj,new Object[]{10});
        Method func3 = classInfo.getMethod("func3", String.class, Integer.class);
        func3.invoke(obj,new Object[]{"aa",10});
    }
}

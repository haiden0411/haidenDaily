package com.huawei.reflect;
import com.huawei.springboot.domain.Apple;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
/**
 * Author：胡灯
 * Date：2020-02-09 21:23
 * Description：<描述>
 */
public class ReflectTest
{

    public static void testReflect() throws Exception
    {
        Class<?> aClass = Class.forName("com.huawei.springboot.domain.Apple");
       /* Method[] declaredMethods = aClass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods)
        {
            System.out.println(declaredMethod.getName());
        }*/
        Object instance = aClass.newInstance();
        Field color = aClass.getDeclaredField("color");
        Field weight = aClass.getDeclaredField("weight");
        color.setAccessible(true);
        color.isAccessible();
        weight.setAccessible(true);
        color.set(instance,"green");
        weight.set(instance,30);
        Method toString = aClass.getDeclaredMethod("toString");
        toString.invoke(instance);
        Constructor<?> gree = aClass.getConstructor(String.class,Integer.class);
        Object instance1 = gree.newInstance("gree", 20);
        System.out.println(instance1.toString());
    }

    public static void testAnnotable(){
        Class<Apple> aClass = Apple.class;
        Table annotation = aClass.getAnnotation(Table.class);
        System.out.println("name:"+annotation.name());
        System.out.println("value:" + annotation.value());
    }
    public static void main(String[] args) throws Exception
    {
        testAnnotable();
    }
}

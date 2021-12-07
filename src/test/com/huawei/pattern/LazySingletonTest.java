package com.huawei.pattern;
import com.huawei.pattern.singleton.*;
import org.aspectj.weaver.ast.Var;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
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
        System.out.println(instance1);
        System.out.println(instance2);
        System.out.println(instance1==instance2);
    }
    @Test
    public void testEnumSingleton2()
    {
        try
        {
            Class<?> clazz = EnumSingleton.class;
            Constructor<?> c = clazz.getDeclaredConstructor(String.class, int.class);
            c.setAccessible(true);
            EnumSingleton enumSingleton = (EnumSingleton) c.newInstance("Tom", 66);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    @Test
    public void testSeriableSingleton() throws Exception
    {
        SeriableSingleton s1 = null;
        SeriableSingleton s2 = SeriableSingleton.getInstance();
        FileOutputStream fos = new FileOutputStream("seriable.obj");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(s2);
        oos.flush();
        oos.close();
        FileInputStream fis = new FileInputStream("seriable.obj");
        ObjectInputStream ois = new ObjectInputStream(fis);
        s1 = (SeriableSingleton) ois.readObject();
        ois.close();
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s1==s2);
    }

    @Test
    public void testThreadLocalSingleton(){
        System.out.println(ThreadLocalSingleton.getInstance());
        System.out.println(ThreadLocalSingleton.getInstance());
        System.out.println(ThreadLocalSingleton.getInstance());
        System.out.println(ThreadLocalSingleton.getInstance());
        System.out.println(ThreadLocalSingleton.getInstance());
        Thread t1 = new Thread(new ExectorThread());
        Thread t2 = new Thread(new ExectorThread());
        t1.start();
        t2.start();
        System.out.println("end");
    }

}

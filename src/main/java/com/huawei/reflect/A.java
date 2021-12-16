package com.huawei.reflect;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;
/**
 * Author：胡灯
 * Date：2021-04-01 19:16
 * Description：<描述>
 */
public class A
{
    public A()
    {
        System.out.println("This is A：");
    }
    public A(Integer m)
    {
        System.out.println("this is " + m);
    }
    public A(String s, Integer m)
    {
        System.out.println(s + "：" + m);
    }
    public void func1()
    {
        System.out.println("This is func1：");
    }
    public void func2(Integer m)
    {
        System.out.println("This is func2：" + m);
    }
    public void func3(String s, Integer m)
    {
        System.out.println("This is func3：" + s + m);
    }
    private void func4(String s, String m)
    {
        System.out.println("This is func4:"+s+m);
    }
    public static void main(String[] args) throws Exception
    {
        Class classInfo = Class.forName("com.huawei.reflect.A");
        Constructor[] cons = classInfo.getConstructors();
        for (Constructor con : cons)
        {
            System.out.println(con.getParameterCount());
        }
        cons[2].newInstance();
        cons[1].newInstance(new Object[]{10});
        cons[0].newInstance(new Object[]{"haiden", 10});
        System.out.println("第二种方法-----------");
        Constructor con1 = classInfo.getConstructor();
        con1.newInstance();
        Constructor con2 = classInfo.getConstructor(Integer.class);
        con2.newInstance(15);
        Constructor con3 = classInfo.getConstructor(String.class, Integer.class);
        con3.newInstance("gree", 199);
        System.out.println("执行方法fun1-----------");
        Method m1 = classInfo.getMethod("func1",null);
        Object obj = classInfo.getConstructor().newInstance();
        m1.invoke(obj,new Object[]{});
        System.out.println("执行方法fun2-----------");
        Method m2 = classInfo.getMethod("func2", Integer.class);
        m2.invoke(obj,10);
        System.out.println("执行方法fun3-----------");
        Method m3 = classInfo.getMethod("func3", new Class[]{String.class,Integer.class});
        m3.invoke(obj,"marry",29);
        System.out.println("测试方法-------");
        process("com.huawei.reflect.A","func1",new Object[]{});
        process("com.huawei.reflect.A","func2",new Object[]{10});
        process("com.huawei.reflect.A","func3",new Object[]{"marry",20});
    }

    public static Object process(String className, String funcName, Object[] para) throws Exception
    {
        Class classInfo = Class.forName(className);
        Method method = null;
        Class[] classes = null;
        if (para == null)
        {
            method = classInfo.getMethod(funcName);
            para = new Object[0];
            classes = new Class[0];
        }else {
            classes = Stream.of(para).map(Object::getClass).collect(Collectors.toList()).toArray(new Class[para.length]);
            method = classInfo.getMethod(funcName,classes);
        }
        Object obj = classInfo.getConstructor().newInstance();
        Object invoke = method.invoke(obj, para);
        return invoke ;
    }
}

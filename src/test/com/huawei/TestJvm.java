package com.huawei;
import com.huawei.springboot.domain.Apple;
import org.junit.Test;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
/**
 * Author：胡灯
 * Date：2020-02-19 11:09
 * Description：<描述>
 */
public class TestJvm
{
    @Test
    public void testSoftRefernce()
    {
        Apple apple = new Apple();
        SoftReference<Apple> softReference = new SoftReference<>(apple);
        apple = null;
        System.gc();
        Apple apple1 = softReference.get();
        System.out.println(apple1);
    }

    @Test
    public void testWeakRefernce()
    {
        Apple apple = new Apple();
        WeakReference<Apple> weakReference = new WeakReference<>(apple);
        apple = null;
        System.gc();
        Apple apple1 = weakReference.get();
        System.out.println(apple1);
    }

    @Test
    public void testWeakRefernce1()
    {
       Apple apple = new Apple();
    }
    public void testPhameRefernce()
    {
    }
}

package com.huawei.Daliy.crazy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
/**
 * Author：胡灯
 * Date：2020-12-08 22:09
 * Description：<描述>
 */
public class MyUtils
{
    public static <T> T copy(Collection<? super T> dest, Collection<T> src){
        T last = null;
        for (T t : src)
        {
            last = t;
            dest.add(t);
        }
        return last;
    }
    public static void main(String[] args)
    {
        List<Number> ln = new ArrayList<>();
        List<Integer> li = new ArrayList<>();
        li.add(5);
        Integer copy = copy(ln, li);
        System.out.println(copy);

    }
}

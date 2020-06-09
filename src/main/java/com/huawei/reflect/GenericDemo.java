package com.huawei.reflect;
import oracle.sql.NUMBER;

import java.util.ArrayList;
import java.util.List;
/**
 * Author：胡灯
 * Date：2020-02-13 11:07
 * Description：<描述>
 */
public class GenericDemo
{

    public static void down(List<? extends  Number> l){
        for (Object number : l)
        {
            System.out.println(number);
        }

    }
    public static void up(List<? super   Number> l){
        for (Object number : l)
        {
            System.out.println(number);
        }

    }

    public static void show(List<?> l){
        for (Object number : l)
        {
            System.out.println(number);
        }

    }

    public static void main(String[] args)
    {
        List<String> l1 = new ArrayList<>();
        List<Double> l2 = new ArrayList<>();
        List<Number> l3 = new ArrayList<>();
        List<Object> l4 = new ArrayList<>();
        //up(l1);error
        //up(l2);error
        up(l3);
        up(l4);

       //down(l1);
       down(l2);
       down(l3);
       //down(l4);

        show(l1);
        show(l2);
        show(l2);
        show(l3);

        List<? super Number> test1 = new ArrayList<>();
        Integer i1 = new Integer(10);

    }
}

package com.huawei.Daliy.Thr_20191127;
import java.time.LocalDate;
import java.util.Stack;
/**
 * Author：胡灯
 * Date：2019-11-27 22:28
 * Description：<描述>
 */
public class TestStack
{
    public static void main(String[] args)
    {
        Stack stack = new Stack();
        System.out.println("aa");
        LocalDate date = LocalDate.of(2019,12,11);
        System.out.println(date.minusMonths(79));
    }
}

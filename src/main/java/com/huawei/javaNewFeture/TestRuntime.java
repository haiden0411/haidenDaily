package com.huawei.javaNewFeture;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.BiPredicate;
import java.util.function.Function;
/**
 * Author：胡灯
 * Date：2019-08-22 22:03
 * Description：<描述>
 */
public class TestRuntime
{
    public static void main(String[] args)
    {
        //String shPath = args[0];
        String pythonPath = "C:\\test\\testPython\\test.py";
        Process exec = null;
        //超时后不会异常。
        try
        {
           exec = Runtime.getRuntime().exec("python "+pythonPath);
           exec.waitFor(10, TimeUnit.SECONDS);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        System.out.println("脚本执行完毕");

    }

    @Test
    public void test01(){
        List<String> str = Arrays.asList("a","b","A","B");
        //str.sort((o1, o2) -> o1.compareToIgnoreCase(o2));
        str.sort(String::compareToIgnoreCase);
        Function<String, Integer> stringToInteger = Integer::parseInt;
        BiPredicate<List<String>, String> contains = List::contains;
    }
}

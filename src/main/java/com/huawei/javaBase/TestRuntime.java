package com.huawei.javaBase;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

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
}

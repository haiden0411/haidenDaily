package com.huawei.Daliy.exception;
import java.io.IOException;
/**
 * Author：胡灯
 * Date：2021-04-05 9:30
 * Description：<描述>
 */
public class ExceptionCheck implements Cloneable
{
    private final int age;

    public ExceptionCheck(int age)
    {
        this.age = age;
    }
    public static void test01(){
        throw new RuntimeException("test01");
    }

    public static void test02() throws IOException
    {
        throw new IOException("aa");
    }
    public static void test03(){
        throw new ArrayIndexOutOfBoundsException("test03");
    }
    public static String test04(){
        try
        {
            System.out.println("start");
            return "aa";
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }finally
        {
            System.out.println("bb");
        }
        return "null";
    }
    public static void test05() throws Exception
    {

    }

    public static void main(String[] args) throws CloneNotSupportedException
    {
        try
        {
            test05();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        ExceptionCheck exceptionCheck = new ExceptionCheck(20);
        ExceptionCheck clone = (ExceptionCheck) exceptionCheck.clone();
        System.out.println(clone.age);
    }
}

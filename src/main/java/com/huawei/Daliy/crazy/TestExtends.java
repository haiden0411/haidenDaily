package com.huawei.Daliy.crazy;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
/**
 * Author：胡灯
 * Date：2020-12-05 9:41
 * Description：<描述>
 */
class BaseClass
{

    public String name;
    public void say()
    {
        System.out.println("I am bash class");
    }
}
class SubClass extends BaseClass
{
    public String name;
    @Override
    public void say()
    {
        super.name = "father";
        System.out.println("I am sub class");
        test1(15,16);
        test1(new Integer(15),new Integer(16));
        System.out.println("name:"+name);
    }

    private void test1(){

    }

    private void test1(int a){

    }
    private void test1(int a,int b){
        System.out.println("dd");
    }

    private void test1(Integer a,int b){
        System.out.println("cc");
    }

    private void test1(Integer b,Integer a){
        System.out.println("aa");
    }

    private void test1(Short b,Integer a){
        System.out.println("bb");
    }

    private void test1(short b,Integer a){
        System.out.println("bb");
    }
}
public class TestExtends
{
    public static void main(String[] args)
    {
        BaseClass a = new SubClass();
        a.say();
        List aa = new ArrayList();
        System.out.println(aa instanceof Set);
        Object bb = "aa";
        System.out.println(bb instanceof String);
        String cc = "aa";
    }
}

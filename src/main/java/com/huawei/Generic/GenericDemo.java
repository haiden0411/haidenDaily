package com.huawei.Generic;
import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;
/**
 * Author：胡灯
 * Date：2020-03-12 21:09
 * Description：<描述>
 */
class IntegerPoint{
    private int x;
    private int y;
    public int getX()
    {
        return x;
    }
    public void setX(int x)
    {
        this.x = x;
    }
    public int getY()
    {
        return y;
    }
    public void setY(int y)
    {
        this.y = y;
    }
}

class FloatPoint{
    private float x;
    private float y;
    public float getX()
    {
        return x;
    }
    public void setX(float x)
    {
        this.x = x;
    }
    public float getY()
    {
        return y;
    }
    public void setY(float y)
    {
        this.y = y;
    }
}




public class GenericDemo
{
    public static void main(String[] args)
    {

        Integer[] i = fun1(1,3,4,5);
        System.out.println(Arrays.toString(i));
        Integer [] result = fun1(i);


    }

    public static <T> T[] fun1(T ... arg){
        return arg;
    }

}

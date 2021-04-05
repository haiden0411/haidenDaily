package com.huawei.pattern.vistor;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.concurrent.Callable;
/**
 * Author：胡灯
 * Date：2021-01-29 23:13
 * Description：<描述>
 */

interface IShape{
    float getArea();
    float getLength();
    Object accept(IVisitor v,String method);

}
interface IVisitor
{
    Object visit(Triangle t,String method);
}
@Data
@NoArgsConstructor
@AllArgsConstructor
class Triangle implements IShape
{

    //三角形三点坐标
    float x1,y1,x2,y2,x3,y3;
    //任意两点坐标
    public float getDist(float u1, float v1,float u2, float v2)
    {
        return (float) Math.sqrt((u1-u2)*(u1-u2)+(v1-v2)*(v1-v2));
    }

    @Override
    public float getArea()
    {
        float a = getDist(x1,y1,x2,y2);
        float b = getDist(x1,y1,x3,y3);
        float c = getDist(x2,y2,x3,y3);
        float s = (a+b+c)/2;
        return (float)Math.sqrt(s*(s-a)*(s-b)*(s-c));//海伦公式求面积
    }
    @Override
    public float getLength()
    {
        float a = getDist(x1,y1,x2,y2);
        float b = getDist(x1,y1,x3,y3);
        float c = getDist(x2,y2,x3,y3);
        return a+b+c;
    }
    @Override
    public Object accept(IVisitor v,String method)
    {
        return v.visit(this,method);
    }

}
@Data
@NoArgsConstructor
@AllArgsConstructor
class Point
{
    float x,y;
}
//求重心坐标
class CenterVistor implements IVisitor
{
    @Override
    public Object visit(Triangle t,String method)
    {
        Point pt = new Point();
        pt.setX((t.x1+t.x2+t.x3)/3);
        pt.setY((t.y1+t.y2+t.y3)/3);
        return pt;
    }
}
class ShapeVistor implements IVisitor
{

    public Object getCenter(Triangle t){ //获取重心坐标
        Point pt = new Point();
        pt.x = (t.x1+t.x2+t.x3)/3;
        pt.y = (t.y1+t.y2+t.y3)/3;
        return pt;
    }

    public Float getInnerCircleR(Triangle t){ //获取内切圆半径
        float area = t.getArea();
        float len = t.getLength();
        return new Float(2.0f*area/len);
    }
    @Override
    public Object visit(Triangle t, String method)
    {
        Object result = null;
        try
        {
            Class classInfo = this.getClass();
            Method mt = classInfo.getMethod(method, Triangle.class);
            result = mt.invoke(this, new Object[]{t});
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
}


public class VistorPattern
{
    public static void main(String[] args)
    {
        IVisitor shapeVistor = new ShapeVistor();
        IShape t = new Triangle(0, 0, 2, 0, 0, 2);
        Point pt = (Point) t.accept(shapeVistor,"getCenter");
        System.out.println("pt = " + pt);
        Float pt2 = (Float) t.accept(shapeVistor, "getInnerCircleR");
        System.out.println("pt2 = " + pt2);
    }
}

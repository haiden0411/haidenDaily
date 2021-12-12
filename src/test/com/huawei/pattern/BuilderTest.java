package com.huawei.pattern;
import com.huawei.pattern.builer.Course;
import com.huawei.pattern.builer.CourseBuilder;

import java.time.temporal.ValueRange;
/**
 * Author：胡灯
 * Date：2021-12-12 9:29
 * Description：<描述>
 */
public class BuilderTest
{
    public static void main(String[] args)
    {
        CourseBuilder builder = new CourseBuilder().addHomeWord("homeWord").addName("java").addNode("node").addPpt("ppt").addVideo("video");
        System.out.println(builder.build());
        // 内部类
        Course.Builder builder1 = new Course.Builder().addHomeWord("homeWord1").addName("java1").addNode("node1").addPpt("ppt1").addVideo("video1");
        System.out.println(builder1.build());
    }
}

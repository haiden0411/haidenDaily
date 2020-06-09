package com.huawei.reflect;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * Author：胡灯
 * Date：2020-02-11 9:18
 * Description：<描述>
 */
@Target(value = ElementType.TYPE)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Table
{
    String name() default "";
    String value() default "";
}

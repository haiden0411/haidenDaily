package com.huawei.javaBase;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

/**
 * Author：胡灯
 * Date：2019-08-29 22:21
 * Description：<描述>
 */
public class Java8Time
{
    public static void main(String[] args)
    {
        LocalDate date = LocalDate.of(2019,8,29);
        System.out.println(date.getYear());
        System.out.println(date.getDayOfYear());
        Month month = date.getMonth();

        System.out.println(date.getDayOfMonth());
        System.out.println(date.lengthOfMonth());
        System.out.println(LocalDate.now());
        System.out.println(date.toString());
        TemporalAdjuster temporalAdjuster = TemporalAdjusters.firstDayOfMonth();
    }


}

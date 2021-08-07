package com.huawei.javaNewFeture;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjusters;
/**
 * Author：胡灯
 * Date：2021-01-23 16:53
 * Description：<描述>
 */
public class Adjusters
{
    public static Temporal adjustInto(Temporal input)
    {
        LocalDate date = LocalDate.from(input);
        int day;
        if (date.getDayOfMonth()<15)
        {
            day = 15;
        }else{
            //最后一天
            day = date.with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth();
        }
        //如果是周六或是周天,则提前到星期五发工资
        date = date.withDayOfMonth(day);
        if (date.getDayOfWeek().equals(DayOfWeek.SATURDAY)||date.getDayOfWeek().equals(DayOfWeek.SUNDAY))
        {
            date = date.with(TemporalAdjusters.previous(DayOfWeek.FRIDAY));
        }

        return input.with(date);
    }
}

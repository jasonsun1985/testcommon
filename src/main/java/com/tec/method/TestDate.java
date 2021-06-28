package com.tec.method;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Date;
import java.util.Locale;

/**
 * @author SUNLEI4
 * @Description
 * @date 2021/6/18
 */
public class TestDate {
    public static void main(String[] args) {
        week();
    }

    private static void week() {
        TemporalField temporalField = WeekFields.of(Locale.CHINESE).dayOfWeek();
        //当周的第一天
        LocalDate weekStart = LocalDate.now().with(temporalField, 1);
        System.out.println(weekStart);
        LocalDate localDate = LocalDate.now();
        System.out.println("localDate.with(DayOfWeek.MONDAY) : " + localDate.with(DayOfWeek.MONDAY));
        //今天
        Date day = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        //这个月的第一天
        Date monthStart = Date.from(localDate.with(TemporalAdjusters.firstDayOfMonth()).atStartOfDay(ZoneId.systemDefault()).toInstant());
        //这个月的最后一天 下个月的第一天
        Date monthEnd = Date.from(localDate.plusMonths(1).with(TemporalAdjusters.firstDayOfMonth()).atStartOfDay(ZoneId.systemDefault()).toInstant());
        //今年的第一天
        Date yearStart = Date.from(localDate.with(TemporalAdjusters.firstDayOfYear()).atStartOfDay(ZoneId.systemDefault()).toInstant());
        //今年的最后一天 下一年的第一天
        Date yearEnd = Date.from(localDate.plusYears(1).with(TemporalAdjusters.firstDayOfYear()).atStartOfDay(ZoneId.systemDefault()).toInstant());
        LocalDate now = LocalDate.now();

        System.out.println(LocalDate.now().atStartOfDay().toLocalDate().getDayOfMonth());
        System.out.println(LocalDate.now().with(DayOfWeek.MONDAY).getDayOfWeek().getValue());
        System.out.println(LocalDate.now().getDayOfWeek().getValue());
        //本月第一天
        LocalDate firstDayOfMonth = LocalDate.of(localDate.getYear(), localDate.getMonthValue(), 1);
        System.out.println("firstday:"+firstDayOfMonth);
        //本月的最后一天
        LocalDate lastDay = localDate.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println("lastDay:"+lastDay);

        Period between = Period.between(firstDayOfMonth, now);
        System.out.println("相隔几天：" + between.getDays());


    }
}

package com.tec.datetime;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

public class DateTimeTest {
    public static void main(String[] args) {
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(getMonth1st(System.currentTimeMillis())), ZoneId.systemDefault());
        System.out.println(localDateTime.format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        System.out.println(Long.valueOf(12));
        String time = "2020-06-02T14:44:10.47";
        LocalDate localDate1 = LocalDateTime.parse(time).toLocalDate();
        System.out.println(localDate1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        System.out.println(localDate1.format(formatter));
        System.out.println(LocalDateTime.now());
        System.out.println(System.currentTimeMillis());
        System.out.println(LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8")));
        System.out.println(Timestamp.valueOf(LocalDateTime.now()).getTime());


    }

    public static long getMonth1st(long timestamp) {
        return Instant.ofEpochMilli(timestamp).atZone(ZoneId.systemDefault())
                .with(TemporalAdjusters.firstDayOfMonth()).toInstant().toEpochMilli();
    }

    public static LocalDateTime getDateTimeOfTimestamp(long timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }

    public void testJava8DateFormat() {
        DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDateTime localDateTime = LocalDateTime.parse("2019-07-31 00:00:00", dateTimeFormatter1);
        LocalDate localDate = LocalDate.parse("2019-07-31", dateTimeFormatter2);
        Date date = Date.from(LocalDateTime.parse("2019-07-31 00:00:00", dateTimeFormatter1).atZone(ZoneId.systemDefault()).toInstant());


        String strDateTime = "2019-07-31 00:00:00";
        String strDate = "2019-07-31";
        Long timestamp = 1564502400000L;

/** LocalDateTime 转 LocalDate */
        System.out.println("LocalDateTime 转 LocalDate: " + localDateTime.toLocalDate());
/** LocalDateTime 转 Long */
        System.out.println("LocalDateTime 转 Long: " + localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
/** LocalDateTime 转 Date */
        System.out.println("LocalDateTime 转 Date: " + Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant()));
/** LocalDateTime 转 String */
        System.out.println("LocalDateTime 转 String: " + localDateTime.format(dateTimeFormatter1));

        System.out.println("-------------------------------");

/** LocalDate 转 LocalDateTime */
        System.out.println("LocalDate 转 LocalDateTime: " + LocalDateTime.of(localDate, LocalTime.parse("00:00:00")));
/** LocalDate 转 Long */
        System.out.println("LocalDate 转 Long: " + localDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli());
/** LocalDate 转 Date */
        System.out.println("LocalDate 转 Date: " + Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
/** LocalDate 转 String */
        System.out.println("LocalDateTime 转 String: " + localDateTime.format(dateTimeFormatter2));

        System.out.println("-------------------------------");

/** Date 转 LocalDateTime */
        System.out.println("Date 转 LocalDateTime: " + LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()));
/** Date 转 Long */
        System.out.println("Date 转 Long: " + date.getTime());
/** Date 转 LocalDate */
        System.out.println("Date 转 LocalDateTime: " + LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).toLocalDate());
/** Date 转 String */
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        System.out.println("Date 转 String: " + sdf.format(date));

        System.out.println("-------------------------------");

/** String 转 LocalDateTime */
        System.out.println("String 转 LocalDateTime: " + LocalDateTime.parse(strDateTime, dateTimeFormatter1));
/** String 转 LocalDate */
        System.out.println("String 转 LocalDate: " + LocalDateTime.parse(strDateTime, dateTimeFormatter1).toLocalDate());
        System.out.println("String 转 LocalDate: " + LocalDate.parse(strDate, dateTimeFormatter2));
/** String 转 Date */
        System.out.println("String 转 Date: " + Date.from(LocalDateTime.parse(strDateTime, dateTimeFormatter1).atZone(ZoneId.systemDefault()).toInstant()));

        System.out.println("-------------------------------");

/** Long 转 LocalDateTime */
        System.out.println("Long 转 LocalDateTime:" + LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault()));
/** Long 转 LocalDate */
        System.out.println("Long 转 LocalDate:" + LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault()).toLocalDate());
    }
}

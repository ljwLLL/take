package com.hpower.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Date;

/**
 * Java8中的时间类
 **/
public class LocalDateTimeUtils {

    /**
     * Date 转 LocalDateTime
     *
     * @param date date对象
     * @return
     */
    public static LocalDateTime convertDateToLDT(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * LocalDateTime 转 Date
     *
     * @param time LocalDateTime对象
     * @return
     */
    public static Date convertLDTToDate(LocalDateTime time) {
        return Date.from(time.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获取指定日期的毫秒
     *
     * @param time LocalDateTime对象
     * @return
     */
    public static Long getMilliByTime(LocalDateTime time) {
        return time.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * 获取指定日期的秒
     *
     * @param time LocalDateTime对象
     * @return
     */
    public static Long getSecondsByTime(LocalDateTime time) {
        return time.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
    }

    /**
     * LocalDateTime 转 String
     *
     * @param time    LocalDateTime对象
     * @param pattern 时间格式 例：yyyy-MM-dd
     * @return
     */
    public static String formatTime(LocalDateTime time, String pattern) {
        return time.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * String 转 LocalDateTime
     *
     * @param pattern    时间格式 例：yyyy-MM-dd
     * @param timeString 时间
     * @return
     */
    public static LocalDateTime StringToLocalDateTime(String pattern, String timeString) {
        return LocalDateTime.parse(timeString, DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 获取当前时间的指定格式
     *
     * @param pattern 时间格式 例：yyyy-MM-dd
     * @return
     */
    public static String formatNow(String pattern) {
        return formatTime(LocalDateTime.now(), pattern);
    }

    /**
     * 日期加上一个数,根据field不同加不同值,field为ChronoUnit.*
     *
     * @param time   LocalDateTime对象
     * @param number 时间个数
     * @param field  时间单位（年、月、日）
     * @return
     */
    public static LocalDateTime plus(LocalDateTime time, long number, TemporalUnit field) {
        return time.plus(number, field);
    }

    /**
     * 日期减去一个数,根据field不同减不同值,field参数为ChronoUnit.*
     *
     * @param time   LocalDateTime对象
     * @param number 时间个数
     * @param field  时间单位（年、月、日）
     * @return
     */
    public static LocalDateTime minu(LocalDateTime time, long number, TemporalUnit field) {
        return time.minus(number, field);
    }

    /**
     * 获取两个日期的差  field参数为ChronoUnit.*
     *
     * @param startTime
     * @param endTime
     * @param field     单位(年月日时分秒)
     * @return
     */
    public static long betweenTwoTime(LocalDateTime startTime, LocalDateTime endTime, ChronoUnit field) {
        Period period = Period.between(LocalDate.from(startTime), LocalDate.from(endTime));
        if (field == ChronoUnit.YEARS) {
            return period.getYears();
        }
        if (field == ChronoUnit.MONTHS) {
            return period.getYears() * 12 + period.getMonths();
        }
        return field.between(startTime, endTime);
    }

    /**
     * 获取一天的开始时间
     *
     * @param time LocalDateTime对象
     * @return
     */
    public static LocalDateTime getDayStart(LocalDateTime time) {
        return time.withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0);
    }

    /**
     * 获取一天的结束时间
     *
     * @param time
     * @return
     */
    public static LocalDateTime getDayEnd(LocalDateTime time) {
        return time.withHour(23)
                .withMinute(59)
                .withSecond(59)
                .withNano(999999999);
    }

    /**
     * 与当前时间进行比较
     * @param time str时间
     * @param pattern 日期格式
     * @return time >= now() ? true :false
     */
    public static boolean strTimeCompareNow(String time, String pattern){
        return time.compareTo(LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern))) >= 0;
    }

    /**
     * string LocalDate类型进行增减(只支持年月日)
     * @param strLocalDate 时间
     * @param number 时间数
     * @param field 增减时间单位
     * @return
     */
    public static String strLocalDatePlusMinus(String strLocalDate, long number, ChronoUnit field){
        LocalDate localDate = LocalDate.parse(strLocalDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate alterLocalDate;
        if (number > 0) {
            alterLocalDate = localDate.plus(number, field);
        } else {
            alterLocalDate = localDate.minus(number, field);
        }
        return alterLocalDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * 获取两个LocalDate时间差
     * @param startDate
     * @param endDate
     * @param field
     * @return
     */
    public static long betweenTwoTime(LocalDate startDate, LocalDate endDate, ChronoUnit field) {
        Period period = Period.between(startDate, endDate);
        if (field == ChronoUnit.YEARS) {
            return period.getYears();
        }
        if (field == ChronoUnit.MONTHS) {
            return period.getYears() * 12 + period.getMonths();
        }
        return field.between(startDate, endDate);
    }


    /**
     * 测试
     *
     * @param args
     */
//    public static void main(String[] args) {
//        LocalDateTime start = LocalDateTime.of(1993, 10, 13, 11, 11);
//        LocalDateTime end = LocalDateTime.of(1994, 11, 13, 13, 13);
//        //根据ChronoUnit.* 返回相应的时间差值
//        System.out.println("年:" + LocalDateTimeUtils.betweenTwoTime(start, end, ChronoUnit.YEARS));
//        System.out.println("月:" + LocalDateTimeUtils.betweenTwoTime(start, end, ChronoUnit.MONTHS));
//        System.out.println("日:" + LocalDateTimeUtils.betweenTwoTime(start, end, ChronoUnit.DAYS));
//        System.out.println("半日:" + LocalDateTimeUtils.betweenTwoTime(start, end, ChronoUnit.HALF_DAYS));
//        System.out.println("小时:" + LocalDateTimeUtils.betweenTwoTime(start, end, ChronoUnit.HOURS));
//        System.out.println("分钟:" + LocalDateTimeUtils.betweenTwoTime(start, end, ChronoUnit.MINUTES));
//        System.out.println("秒:" + LocalDateTimeUtils.betweenTwoTime(start, end, ChronoUnit.SECONDS));
//        System.out.println("毫秒:" + LocalDateTimeUtils.betweenTwoTime(start, end, ChronoUnit.MILLIS));
//
//        //增加二十分钟
//        System.out.println(LocalDateTimeUtils.plus(LocalDateTime.now(), 20, ChronoUnit.MINUTES));
//        //增加两年
//        System.out.println(LocalDateTimeUtils.formatTime(LocalDateTimeUtils.plus(LocalDateTime.now(), 2, ChronoUnit.YEARS), "yyyy年MM月dd日 HH:mm"));
//
//        System.out.println(LocalDateTimeUtils.getDayStart(LocalDateTime.now()));
//        System.out.println(LocalDateTimeUtils.getDayEnd(LocalDateTime.now()));
//    }

}

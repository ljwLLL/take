package com.hpower.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @author yangyang.jiang
 * @Description:
 * @create 2020/8/3
 * @time 1:21 下午
 */
public class DateUtil {

    /**
     * 日期往后退几个月
     * @return
     */
    public static String monthDown(String month,int size){
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(simpleDateFormat.parse(month));
            calendar.add(Calendar.MONTH,size);
            return simpleDateFormat.format(calendar.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 日期往后退几年
     * @return
     */
    public static String yearDown(String year,int size){
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(simpleDateFormat.parse(year));
            calendar.add(Calendar.YEAR,size);
            return simpleDateFormat.format(calendar.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}

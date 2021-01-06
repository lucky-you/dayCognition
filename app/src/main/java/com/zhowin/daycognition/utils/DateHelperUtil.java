package com.zhowin.daycognition.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.SystemClock;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


/**
 * author : zho
 * date  ：2021/1/6
 * desc ：时间戳帮助工具类
 */
public class DateHelperUtil {


    public static String getCurrentDayTime() {
        Date date = new Date();// 创建一个时间对象，获取到当前的时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// 设置时间显示格式
        return sdf.format(date);
    }

    public static String getCurrentDay() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));    //获取东八区时间
        int year = calendar.get(Calendar.YEAR);    //获取年,当前年份
        int month = calendar.get(Calendar.MONTH) + 1;   //获取月份，0表示1月份
        int day = calendar.get(Calendar.DAY_OF_MONTH);    //获取当前天数
        int hour = calendar.get(Calendar.HOUR_OF_DAY);       //获取当前小时
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        calendar.set(year, month, day, hour, minute, second);
        return year + "-" + month + "-" + day + " " + hour + ":" + minute;
    }
}
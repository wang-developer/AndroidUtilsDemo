package com.developer.wang.utils;

/**
 * Created by aoo on 2017/6/19.
 */

import android.content.ContentResolver;
import android.content.Context;
import android.text.TextUtils;


import com.developer.wang.utils.string.ConstellationUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

;

/**
 * 日期工具类(未特别说明的均为系统默认时区下的时间)
 */
public class DateUtil {
    /**
     * 1s==1000ms
     */
    private final static int TIME_MILLISECONDS = 1000;
    /**
     * 时间中的分、秒最大值均为60
     */
    private final static int TIME_NUMBERS = 60;
    /**
     * 时间中的小时最大值
     */
    private final static int TIME_HOURSES = 24;
    /**
     * 格式化日期的标准字符串
     */
    private final static String FORMAT = "yyyy-MM-dd HH:mm:ss";
    private final static String FORMAT_Y_M_D = "yyyy-MM-dd";
    private final static String FORMAT_M_D_Y = "MM/dd/yyyy";

    /**
     * 获取时区信息
     */
    public static TimeZone getTimeZone() {
        return TimeZone.getDefault();
    }

    /**
     * 将日期字符串转换为Date对象
     *
     * @param date 日期字符串，必须为"yyyy-MM-dd HH:mm:ss"
     * @return 日期字符串的Date对象表达形式
     */
    public static Date parseDate(String date) {
        return parseDate(date, FORMAT);
    }

    /**
     * 将日期字符串转换为Date对象
     *
     * @param date   日期字符串，必须为"yyyy-MM-dd HH:mm:ss"
     * @param format 格式化字符串
     * @return 日期字符串的Date对象表达形式
     */
    public static Date parseDate(String date, String format) {
        Date dt = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        try {
            dt = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return dt;
    }

    /**
     * 将Date对象转换为指定格式的字符串
     *
     * @param date Date对象
     * @return Date对象的字符串表达形式"yyyy-MM-dd HH:mm:ss"
     */
    public static String formatDate(Date date) {
        return formatDate(date, FORMAT);
    }

    public static String formatDateYYMMDD(Date date) {
        return formatDate(date, FORMAT_Y_M_D);
    }

    public static String formatDateMMDDYY(Date date) {
        return formatDate(date, FORMAT_M_D_Y);
    }

    /**
     * 将Date对象转换为指定格式的字符串
     *
     * @param date Date对象
     * @return Date对象的字符串表达形式
     */
    public static String formatDate(Date date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    /**
     * 格式化日期
     *
     * @return 日期字符串"yyyy-MM-dd HH:mm:ss"
     */
    public static String formatUnixTime(long unixTime) {
        return formatUnixTime(unixTime, FORMAT);
    }

    /**
     * 格式化日期
     *
     * @return 日期字符串
     */
    public static String formatUnixTime(long unixTime, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(unixTime);
    }

    /**
     * 将GMT日期格式化为系统默认时区的日期字符串表达形式
     *
     * @param gmtUnixTime GTM时间戳
     * @return 日期字符串"yyyy-MM-dd HH:mm:ss"
     */
    public static String formatGMTUnixTime(long gmtUnixTime) {
        return formatGMTUnixTime(gmtUnixTime, FORMAT);
    }

    public static String getCurrentUnixTimeByGMT(long unixTimeGMT) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long unixTime = unixTimeGMT * 1000L + TimeZone.getDefault().getRawOffset();
        return dateFormat.format(unixTime);
    }

    /**
     * 将GMT日期格式化为系统默认时区的日期字符串表达形式
     *
     * @param gmtUnixTime GTM时间戳
     * @param format      格式化字符串
     * @return 日期字符串"yyyy-MM-dd HH:mm:ss"
     */
    public static String formatGMTUnixTime(long gmtUnixTime, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(gmtUnixTime + TimeZone.getDefault().getRawOffset());
    }

    /**
     * 获取时间戳的Date表示形式
     *
     * @param unixTime unix时间戳
     * @return Date对象
     */
    public static Date getDate(long unixTime) {
        return new Date(unixTime);
    }

    /**
     * 获取GMT时间戳的Date表示形式（转换为Date表示形式后，为系统默认时区下的时间）
     *
     * @param gmtUnixTime GMT Unix时间戳
     * @return Date对象
     */
    public static Date getGMTDate(long gmtUnixTime) {
        return new Date(gmtUnixTime + TimeZone.getDefault().getRawOffset());
    }

    /**
     * 将系统默认时区的Unix时间戳转换为GMT Unix时间戳
     *
     * @param unixTime unix时间戳
     * @return GMT Unix时间戳
     */
    public static long getGMTUnixTime(long unixTime) {
        return unixTime - TimeZone.getDefault().getRawOffset();
    }

    /**
     * 将GMT Unix时间戳转换为系统默认时区的Unix时间戳
     *
     * @param gmtUnixTime GMT Unix时间戳
     * @return 系统默认时区的Unix时间戳
     */
    public static long getCurrentTimeZoneUnixTime(long gmtUnixTime) {
        return gmtUnixTime + TimeZone.getDefault().getRawOffset();
    }

    /**
     * 获取当前时间的GMT Unix时间戳
     *
     * @return 当前的GMT Unix时间戳
     */
    public static long getGMTUnixTimeByCalendar() {
        Calendar calendar = Calendar.getInstance();
        // 获取当前时区下日期时间对应的时间戳
        long unixTime = calendar.getTimeInMillis();
        // 获取标准格林尼治时间下日期时间对应的时间戳
        long unixTimeGMT = unixTime - TimeZone.getDefault().getRawOffset();
        return unixTimeGMT / 1000L;
    }

    /**
     * 获取当前时间的Unix时间戳
     *
     * @return 当前的Unix时间戳
     */
    public static long getUnixTimeByCalendar() {
        Calendar calendar = Calendar.getInstance();
        // 获取当前时区下日期时间对应的时间戳
        long unixTime = calendar.getTimeInMillis();
        return unixTime;
    }

    /**
     * 获取更改时区后的时间
     *
     * @param date    时间
     * @param oldZone 旧时区
     * @param newZone 新时区
     * @return 时间
     */
    public static Date changeTimeZone(Date date, TimeZone oldZone, TimeZone newZone) {
        Date dateTmp = null;
        if (date != null) {
            int timeOffset = oldZone.getRawOffset() - newZone.getRawOffset();
            dateTmp = new Date(date.getTime() - timeOffset);
        }
        return dateTmp;
    }

    /**
     * 将总秒数转换为时分秒表达形式
     *
     * @param seconds 任意秒数
     * @return %s小时%s分%s秒
     */
    public static String formatTime(long seconds) {
        long hh = seconds / TIME_NUMBERS / TIME_NUMBERS;
        long mm = (seconds - hh * TIME_NUMBERS * TIME_NUMBERS) > 0 ? (seconds - hh * TIME_NUMBERS * TIME_NUMBERS) / TIME_NUMBERS : 0;
        long ss = seconds < TIME_NUMBERS ? seconds : seconds % TIME_NUMBERS;
        return (hh == 0 ? "" : (hh < 10 ? "0" + hh : hh) + "小时")
                + (mm == 0 ? "" : (mm < 10 ? "0" + mm : mm) + "分")
                + (ss == 0 ? "" : (ss < 10 ? "0" + ss : ss) + "秒");
    }
    public static String formatTime2(long seconds) {
        long hh = seconds / TIME_NUMBERS / TIME_NUMBERS;
        long mm = (seconds - hh * TIME_NUMBERS * TIME_NUMBERS) > 0 ? (seconds - hh * TIME_NUMBERS * TIME_NUMBERS) / TIME_NUMBERS : 0;
        long ss = seconds < TIME_NUMBERS ? seconds : seconds % TIME_NUMBERS;
        return (hh == 0 ? "00," : (hh < 10 ? "0" + hh : hh) + ",")
                + (mm == 0 ? "00," : (mm < 10 ? "0" + mm : mm) + ",")
                + (ss == 0 ? "00" : (ss < 10 ? "0" + ss : ss) + "");
    }

    /**
     * 获取当前时间距离指定日期时差的大致表达形式
     *
     * @return 时差的大致表达形式
     */
    public static String getDiffTime(long date) {
        String strTime = "很久很久以前";
        long time = Math.abs(new Date().getTime() - date);
        // 一分钟以内
        if (time < TIME_NUMBERS * TIME_MILLISECONDS) {
            strTime = "刚刚";
        } else {
            int min = (int) (time / TIME_MILLISECONDS / TIME_NUMBERS);
            if (min < TIME_NUMBERS) {
                if (min < 15) {
                    strTime = "一刻钟前";
                } else if (min < 30) {
                    strTime = "半小时前";
                } else {
                    strTime = "1小时前";
                }
            } else {
                int hh = min / TIME_NUMBERS;
                if (hh < TIME_HOURSES) {
                    strTime = hh + "小时前";
                } else {
                    int days = hh / TIME_HOURSES;
                    if (days <= 6) {
                        strTime = days + "天前";
                    } else {
                        int weeks = days / 7;
                        if (weeks < 3) {
                            strTime = weeks + "周前";
                        }
                    }
                }
            }
        }

        return strTime;
    }


    public static String getDurationTimer(int time) {
        int f = time / 60;
        int m = time % 60;
        int s = f / 60;
        if (s > 0) {
            f = f % 60;
            return (s < 10 ? "0" + s : s) + ":" + (f < 10 ? "0" + f : f) + ":" + (m < 10 ? "0" + m : m);
        } else {
            return (f < 10 ? "0" + f : f) + ":" + (m < 10 ? "0" + m : m);
        }
    }


    //出生日期字符串转化成Date对象
    public static Date parse(String strDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_Y_M_D);
        return sdf.parse(strDate);
    }

    //由出生日期获得年龄
    public static int getAge(String birthDay) throws Exception {

        Date day = parse(birthDay);

        Calendar cal = Calendar.getInstance();

        if (cal.before(day)) {
            throw new IllegalArgumentException(
                    "The birthDay is before Now.It's unbelievable!");
        }
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH);
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(day);

        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

        int age = yearNow - yearBirth;

        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) age--;
            } else {
                age--;
            }
        }
        return age;
    }

    //由出生日期获得星座
    public static String getConstellation(Context context, String birthDay) throws Exception {

        Date day = parse(birthDay);

        Calendar cal = Calendar.getInstance();

        if (cal.before(day)) {
            throw new IllegalArgumentException(
                    "The birthDay is before Now.It's unbelievable!");
        }
        cal.setTime(day);
        int monthNow = cal.get(Calendar.MONTH) + 1;
        int dayNow = cal.get(Calendar.DATE);
        return ConstellationUtils.getInstance(context).getConstellation(monthNow, dayNow);
    }

    /**
     * 标准时间 2017-07-31 16:59:34或者2017-07-31T16:59:34转化为2017.07.31
     *
     * @param timeStandard
     * @return
     */
    public static String getDateTimeToYYYYMMDD(String timeStandard) {
        if (timeStandard != null && timeStandard.length() >= 10) {
            timeStandard = timeStandard.substring(0, 10);
            timeStandard = timeStandard.replaceAll("-", ".");
        }
        return timeStandard;
    }

    /**
     * 字符串转为字符串符合标准格式"YYYY-MM-DD HH:MM:SS"
     *
     * @param dateTime 标准时间格式 "YYYY-MM-DD HH:MM:SS"
     * @return java.util.Date
     */
    public static Date toDate(String dateTime) {
        int index = dateTime.indexOf(" ");
        if (index == -1) return null;
        String date = dateTime.substring(0, index);
        String time = dateTime.substring(index + 1);

        return toDate(date, time);
    }

    /**
     * 字符串转为字符串符合标准格式"YYYY-MM-DD HH:MM:SS"
     *
     * @param date 标准日期格式 "YYYY-MM-DD"
     * @param time 标准时间格式 "HH:MM:SS"
     * @return java.util.Date
     */
    public static Date toDate(String date, String time) {
        if (date == null || time == null) {
            return null;
        }

        int dateSlash1 = date.indexOf("-");
        int dateSlash2 = date.lastIndexOf("-");

        if (dateSlash1 <= 0 || dateSlash1 == dateSlash2) {
            return null;
        }

        int timeColon1 = time.indexOf(":");
        int timeColon2 = time.lastIndexOf(":");

        if (timeColon1 <= 0 || timeColon1 == timeColon2) {
            return null;
        }

        String year = date.substring(0, dateSlash1);
        String month = date.substring(dateSlash1 + 1, dateSlash2);
        String day = date.substring(dateSlash2 + 1);

        String hour = time.substring(0, timeColon1);
        String minute = time.substring(timeColon1 + 1, timeColon2);
        String second = time.substring(timeColon2 + 1);

        return toDate(year, month, day, hour, minute, second);
    }

    /**
     * 通过标准时间字符串输入生成java.util.Date
     *
     * @param yearStr
     * @param monthStr
     * @param dayStr
     * @param hourStr
     * @param minuteStr
     * @param secondStr
     * @return java.util.Date
     */
    public static Date toDate(String yearStr, String monthStr,
                              String dayStr, String hourStr, String minuteStr, String secondStr) {
        int year, month, day, hour, minute, second;

        try {
            year = Integer.parseInt(yearStr);
            month = Integer.parseInt(monthStr);
            day = Integer.parseInt(dayStr);
            hour = Integer.parseInt(hourStr);
            minute = Integer.parseInt(minuteStr);
            second = Integer.parseInt(secondStr);
        } catch (Exception e) {
            return null;
        }
        return toDate(year, month, day, hour, minute, second);
    }

    /**
     * 通过标准时间int 输入生成java.util.Date
     *
     * @param year
     * @param month
     * @param day
     * @param hour
     * @param minute
     * @param second
     * @return java.util.Date
     */
    public static Date toDate(int year, int month, int day, int hour,
                              int minute, int second) {
        Calendar calendar = Calendar.getInstance();

        try {
            calendar.set(year, month - 1, day, hour, minute, second);
        } catch (Exception e) {
            return null;
        }
        return calendar.getTime();
    }

    public static long getcurrentTimeMillis() {
        return System.currentTimeMillis();
    }

    /**
     * 获取聊天时间
     *
     * @param timestamp
     * @returnt
     */
//    public static String getChatShowTime(long timestamp, boolean isNeedTime,Context context) {
//        return DateformatTime(getDate(timestamp), isNeedTime,context);
//    }

    /**
     * 日期格式化
     *
     * @return
     */
//    public static String DateformatTime(Date date, boolean isNeedTime,Context context) {
//        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        //转换为日期
//        long time = date.getTime();
//        int hours = date.getHours();
//
//        String suffixStr = hours >= 12 ? context.getString(R.string.time_pm) :context.getString(R.string.time_am);
//        if (isThisYear(date)) {//今年
////            aa 中文系统显示的 上午 下午
//            if (isToday(date)) { //今天
//                if (is24Hours())
//                    return new SimpleDateFormat("HH:mm").format(date);
//                else
//                    return new SimpleDateFormat("hh:mm").format(date) + suffixStr;
////                }
//            } else {
//                if (isYestYesterday(date)) {//昨天，显示昨天
//                    if (isNeedTime) {
//                        if (is24Hours())
//                            return  context.getResources().getString(R.string.IM_TimeYesterday)+ new SimpleDateFormat("HH:mm").format(date);
//                        else
//                            return context.getResources().getString(R.string.IM_TimeYesterday) + new SimpleDateFormat("hh:mm").format(date) + suffixStr;
//                    } else
//                        return context.getResources().getString(R.string.IM_TimeYesterday);
//                } else if (isThisWeek(date)) {//本周,显示周几
//                    String weekday = null;
//                    if (date.getDay() == 1) {
//                        weekday = context.getResources().getString(R.string.IM_WeekMonday);
//                    }
//                    if (date.getDay() == 2) {
//                        weekday = context.getResources().getString(R.string.IM_WeekTuesday);
//                    }
//                    if (date.getDay() == 3) {
//                        weekday = context.getResources().getString(R.string.IM_WeekWednesday);
//                    }
//                    if (date.getDay() == 4) {
//                        weekday = context.getResources().getString(R.string.IM_WeekThursday);
//                    }
//                    if (date.getDay() == 5) {
//                        weekday = context.getResources().getString(R.string.IM_WeekFriday);
//                    }
//                    if (date.getDay() == 6) {
//                        weekday = context.getResources().getString(R.string.IM_WeekSaturday);
//                    }
//                    if (date.getDay() == 0) {
//                        weekday = context.getResources().getString(R.string.IM_WeekSunday);
//                    }
//                    if (isNeedTime) {
//                        if (is24Hours())
//                            return weekday + " " + new SimpleDateFormat("HH:mm").format(date);
//                        else
//                            return weekday + " " + new SimpleDateFormat("hh:mm").format(date) + suffixStr;
//                    } else
//                        return weekday;
//                } else {
//                    if (isNeedTime) {
//                        if (is24Hours())
//                            return new SimpleDateFormat("MM-dd HH:mm").format(date);
//                        else
//                            return new SimpleDateFormat("MM-dd hh:mm").format(date) + suffixStr;
//                    } else {
//                        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
//                        return sdf.format(date);
//                    }
//                }
//            }
//        } else {
//            if (isNeedTime) {
//                if (is24Hours())
//                    return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date);
//                else
//                    return new SimpleDateFormat("yyyy-MM-dd hh:mm").format(date) + suffixStr;
//            } else {
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//                return sdf.format(date);
//            }
//        }
//    }

//    public static boolean is24Hours() {
//        try {
//            ContentResolver cv = Application.getContext().getContentResolver();
//            String strTimeFormat = android.provider.Settings.System.getString(cv,
//                    android.provider.Settings.System.TIME_12_24);
//            if (strTimeFormat == null)return false;
//            if (strTimeFormat.equals("24"))
//                return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }


    /**
     * String型时间戳格式化
     *
     * @return
     */
    public static String LongFormatTime(String time) {
        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //转换为日期
        Date date = new Date();
        date.setTime(Long.parseLong(time));
        if (isThisYear(date)) {//今年
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
            if (isToday(date)) { //今天
                int minute = minutesAgo(Long.parseLong(time));
                if (minute < 60) {//1小时之内
                    if (minute <= 1) {//一分钟之内，显示刚刚
                        return "刚刚";
                    } else {
                        return minute + "分钟前";
                    }
                } else {
                    return simpleDateFormat.format(date);
                }
            } else {
                if (isYestYesterday(date)) {//昨天，显示昨天
                    return "昨天 " + simpleDateFormat.format(date);
                } else if (isThisWeek(date)) {//本周,显示周几
                    String weekday = null;
                    if (date.getDay() == 1) {
                        weekday = "周一";
                    }
                    if (date.getDay() == 2) {
                        weekday = "周二";
                    }
                    if (date.getDay() == 3) {
                        weekday = "周三";
                    }
                    if (date.getDay() == 4) {
                        weekday = "周四";
                    }
                    if (date.getDay() == 5) {
                        weekday = "周五";
                    }
                    if (date.getDay() == 6) {
                        weekday = "周六";
                    }
                    if (date.getDay() == 0) {
                        weekday = "周日";
                    }
                    return weekday + " " + simpleDateFormat.format(date);
                } else {
                    SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");
                    return sdf.format(date);
                }
            }
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            return sdf.format(date);
        }
    }

    private static int minutesAgo(long time) {
        return (int) ((System.currentTimeMillis() - time) / (60000));
    }

    private static boolean isToday(Date date) {
        Date now = new Date();
        return (date.getYear() == now.getYear()) && (date.getMonth() == now.getMonth()) && (date.getDate() == now.getDate());
    }

    private static boolean isYestYesterday(Date date) {
        Date now = new Date();
        return (date.getYear() == now.getYear()) && (date.getMonth() == now.getMonth()) && (date.getDate() + 1 == now.getDate());
    }

    private static boolean isThisWeek(Date date) {
        Date now = new Date();
        if ((date.getYear() == now.getYear()) && (date.getMonth() == now.getMonth())) {
            if (now.getDay() - date.getDay() < now.getDay() && now.getDate() - date.getDate() > 0 && now.getDate() - date.getDate() < 7) {
                return true;
            }
        }
        return false;
    }

    private static boolean isThisYear(Date date) {
        Date now = new Date();
        return date.getYear() == now.getYear();
    }

    private static final int SECONDS_OF_1MINUTE = 60;

    private static final int SECONDS_OF_1HOUR = 60 * 60;

    private static final int SECONDS_OF_1DAY = 24 * 60 * 60;

    private static final int SECONDS_OF_3DAYS = SECONDS_OF_1DAY * 3;


    /**
     * 获取活跃时间
     * @param context
     * @param mStartTime 开始时间 格式为：yyyy-MM-dd HH:mm:ss
     * @param mEndTime   结束时间 格式为：yyyy-MM-dd HH:mm:ss
     * @return
     */
//    public static String getTimeRange(Context context,String mStartTime, String mEndTime)
//    {
//        try {
//            SimpleDateFormat sdf = new SimpleDateFormat(FORMAT);
//            Date startDate = sdf.parse(mStartTime);
//            Date endDate = sdf.parse(mEndTime);
//            /**除以1000是为了转换成秒*/
//            return getTimeRange(context,startDate.getTime(),endDate.getTime());
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return "";
//    }


    /**
     * 获取活跃时间
     * @param context
     * @param startTimeL 开始时间 时间戳
     * @param endTimeL   结束时间 时间戳
     * @return
     */
//    public static String getTimeRange(Context context,long startTimeL, long endTimeL)
//    {
//        //IM服务器断开后，超过5分钟的开始算作不在线;此处处理startTimeL加5分钟，差值为负数则显示当前活跃
//        startTimeL = UserUtils.getRealActiveTime(startTimeL);
//        long   between=(endTimeL - startTimeL)/1000;
//        int   elapsedTime= (int) (between);
//        if (elapsedTime < SECONDS_OF_1MINUTE) {
//
//            return context.getResources().getString(R.string.active_now);
//        }
//        if (elapsedTime < SECONDS_OF_1HOUR) {
//
//            int second = elapsedTime / SECONDS_OF_1MINUTE;
//            int stringId = R.string.active_minutes_ago;
//            if(second <= 1)
//                stringId = R.string.active_minute_ago;
//            return context.getResources().getString(stringId,second+"");
//        }
//        if (elapsedTime < SECONDS_OF_1DAY) {
//            int hour = elapsedTime / SECONDS_OF_1HOUR;
//            int stringId = R.string.active_hours_ago;
//            if(hour <= 1)
//                stringId = R.string.active_hour_ago;
//            return context.getResources().getString(stringId,hour+"");
//        }
//        if(elapsedTime < SECONDS_OF_3DAYS){
//            int day = elapsedTime / SECONDS_OF_1DAY;
//            int stringId = R.string.active_days_ago;
//            if(day <= 1)
//                stringId = R.string.active_day_ago;
//            return context.getResources().getString(stringId, day+"");
//
//        }
//
//        if(elapsedTime >= SECONDS_OF_3DAYS){
//
//            return context.getResources().getString(R.string.active_three_days_ago);
//
//        }
//        return "";
//    }

    /**
     * 将标准时间转化为时间戳
     * @param dateTime yyyy-MM-dd HH:mm:ss
     * @return 时间戳
     */
    public static long getDateTimeToLong(String dateTime){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(FORMAT);
            Date startDate = sdf.parse(dateTime);
            return startDate.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }


    /********************************* 计算两个时间的间隔 ****************************/



    private final static long DAY =  60 * 60 * 24L;
    private final static long HOUR =  60 * 60L;
    private final static long MINUTE =  60L;

    /**
     * 日期转换成字符串
     * @param date
     * @return str
     */
    public static String dateToStr(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = format.format(date);
        return str;
    }

    /**
     * 字符串转换成日期
     * @param str
     * @return date
     */
    public static Date strToDate(String str) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    static int count ;
    /**
     * 根据时间查询时间属于哪个时刻
     * @param
     * @return
     */
//    public static String[] natureTime(Date date){
//        String[] strings = new String[2];
//        long imTime = IMClient.getInstance().getServerRealTime();
//        Date imDate = new Date(imTime);
//        LogUtils.INSTANCE.i("DateUtil","imDate:"+imDate.toGMTString());
////
//        Date now = new Date();
//        date.setHours(00);
//        LogUtils.INSTANCE.i("DateUtil","dateDate:"+date.toGMTString());
//        long between = date.getTime()/1000 - imDate.getTime()/1000;
//
//        if (between < DAY){
//            strings[0] = 0+"";
//        }else {
//            long a =   (between - DAY) /DAY;
//            long b = between %  DAY;
//            if (b !=0)
//                a++;
//            strings[0] = a+"";
//        }
//
//        strings[1] = "天";
//
//
//
//        return strings;
//    }



//    public static long[] getTimerInterval(){
//        long[] longs = new long[2];
////        yyyy-MM-dd HH:mm:ss
//        //获取两个时间 的时分秒
//        String[] time = natureTime(getNextMonday());
//
//        LogUtils.INSTANCE.i("DateUtil"," 相差的天数:"+time[0]);
//        if (TextUtils.isEmpty(time[1])){
//            longs[0] = 0;
//            longs[1] = 0;
//        }else {
//            if (time[1].equals("天")){
//                longs[0] = Long.valueOf(time[0]);
//            }else {
//                longs[0] = 0;
//            }
//           String string = formatUnixTime(IMClient.getInstance().getServerRealTime(),"HH:mm:ss");
//
//            String[] strs2 = string.split(":");
//            long currentM = Integer.valueOf(strs2[0]) * 60 * 60 + Integer.valueOf(strs2[1]) * 60  + Integer.valueOf(strs2[2]);
//            long endM =  23 * 60 * 60 + 59 * 60 + 59;
//
//            LogUtils.INSTANCE.i("TAG","currentM = "+currentM +"--endM:"+endM);
//            longs[1] = endM - currentM;
//        }
//
//        return longs;
//    }
    // 获得当前日期与本周日相差的天数
    private static int getMondayPlus(Date gmtCreate) {
        gmtCreate.setHours(0);
        Calendar cd = Calendar.getInstance();
        cd.setTime(gmtCreate);
        // 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1; // 因为按中国礼拜一作为第一天所以这里减1
        if (dayOfWeek == 1) {
            return 0;
        } else {
            return 1 - dayOfWeek;
        }

    }

    // 获得下周星期一的日期
    public static Date getNextMonday() {
        int mondayPlus = getMondayPlus(new Date());

        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus + 7);
        Date monday = currentDate.getTime();

        return monday;

    }




}

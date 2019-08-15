package com.ant.app_utils;

import android.annotation.SuppressLint;
import android.text.TextUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

@SuppressLint("SimpleDateFormat")
public class DateUtil {
    private final static String FORMAT_YYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";

    private final static String FORMAT_YYMMDDHHMM = "yyyy/MM/dd HH:mm";
    private final static String FORMAT_YY_MM_DD = "yyyy-MM-dd";
    public final static String FORMAT_YYMMDD = "yyyyMMdd";
    private final static String FORMAT_YYMM = "yyyy-MM";
    public final static String FORMAT_HHMMSS = "HH:mm:ss";
    public final static String VIDEO_TIME = "mm:ss";


    private final long TIME_DAY = 24 * 60 * 60 * 1000;
    private final long TIME_WEEK = 7 * TIME_DAY;
    //一个月时间
    private final long TIME_MONTH = 30 * TIME_DAY;
    //一个季度
    private final long TIME_THREE_MONTH = 4 * TIME_MONTH;
    //一年
    private final long TIME_YEAR = 12 * TIME_MONTH;


    public static Date stringToDate(String _date, String format) {

        if (null == format || "".equals(format)) {
            format = FORMAT_YYMMDDHHMMSS;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = new Date();
        try {
            if (!TextUtils.isEmpty(_date))
                date = sdf.parse(_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }


    public static String formatViedoTime(String date, String format) {
        String sdf;
        try {
            Date date1 = new Date(Long.valueOf(date) * 1000);
            sdf = new SimpleDateFormat(format).format(date1);
        } catch (Exception e) {
            return "";
        }
        return sdf;
    }

    public static String formatDefaultTime(String date) {

        return formatViedoTime(date, FORMAT_YYMMDDHHMM);
    }

    public static String formatDefaultTime(Long date) {
        String sdf;
        try {
            sdf = new SimpleDateFormat(FORMAT_YYMMDDHHMM).format(new Date(date * 1000));
        } catch (Exception e) {
            return "";
        }
        return sdf;
    }

    public static String timeToS(String date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        SimpleDateFormat sdf2 = new SimpleDateFormat(FORMAT_YY_MM_DD);
        Date time;
        try {
            if (!TextUtils.isEmpty(date)) {
                time = sdf.parse(date);
                String format1 = sdf2.format(time);
                return format1;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            // return date;

        }
        return date;
    }

    public static String dateToString(Date date, String format) {
        if (null == format || "".equals(format)) {
            format = FORMAT_YYMMDDHHMMSS;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);

        return sdf.format(date);
    }

    public static String dateToString(Long date) {


        SimpleDateFormat sdf = new SimpleDateFormat(date + "");

        return sdf.format(date);
    }

    public static String dateToString(Date date, String format, Locale locale) {
        if (null == format || "".equals(format)) {
            format = FORMAT_YYMMDDHHMMSS;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format, locale);

        return sdf.format(date);
    }

    public static Date intToDate(long lDate) {
        Date date = new Date(lDate);
        return date;
    }

    public static Date cstToDate(String dateStr) {
        DateFormat df = new SimpleDateFormat(
                "EEE MMM dd HH:mm:ss '+0800' yyyy", Locale.CHINA);// CST格式
        Date date = null;
        try {
            date = df.parse(dateStr);// parse函数进行转换
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date;
    }

    public static String getDateStr(long times, String format) {
        if (times == 0)
            return "";
        Date date = intToDate(times);
        return dateToString(date, format);
    }

    public static Date getDate(int year, int month, int weekInMonth,
                               int dayInWeek) {
        Calendar date = Calendar.getInstance();
        date.clear();
        date.set(Calendar.YEAR, year);
        date.set(Calendar.MONTH, month - 1);
        date.set(Calendar.DAY_OF_WEEK_IN_MONTH, weekInMonth);
        date.set(Calendar.DAY_OF_WEEK, dayInWeek + 1);
        return date.getTime();
    }

    public static Date getDate(int month, int weekInMonth, int dayInWeek) {
        Calendar date = Calendar.getInstance();
        int year = date.get(Calendar.YEAR);
        date.clear();
        date.set(Calendar.YEAR, year);
        date.set(Calendar.MONTH, month - 1);
        date.set(Calendar.DAY_OF_WEEK_IN_MONTH, weekInMonth);
        date.set(Calendar.DAY_OF_WEEK, dayInWeek + 1);
        return date.getTime();
    }

    public static String getDate(int month, int weekInMonth, int dayInWeek,
                                 String format) {
        Date date = getDate(month, weekInMonth, dayInWeek);
        return getDateStr(date.getTime(), format);
    }

    public final static long ONE_MINUTE = 1000 * 60;
    public final static long ONE_HOUR = ONE_MINUTE * 60;
    public final static long ONE_DAY = ONE_HOUR * 24;

    public static String getFormatTime(long time) {
        long current = System.currentTimeMillis();
        long inteval = current - time;
        if (inteval < ONE_MINUTE) {
            return "刚刚";
        } else if (inteval < ONE_HOUR) {
            return inteval / ONE_MINUTE + "分钟前";
        } else if (inteval < ONE_DAY) {
            return inteval / ONE_HOUR + "小时前";// getDateStr(time, "HH:mm");
        } else {
            if (isTheSameYear(time)) {
                // if(isTheSameWeak(time)){
                return getDateStr(time, "MM.dd HH:mm");
                // } else {
                // return getDateStr(time, "MM/dd HH:mm");
                // }
            } else {
                return getDateStr(time, "yyyy.MM.dd HH:mm");// "yyyy/MM/dd HH:mm");
            }
        }

//        if (inteval < ONE_DAY) {
//            long hour = inteval / ONE_HOUR;
//            if (hour <= 2) {
//                return "刚刚";
//            }
//            return inteval / ONE_HOUR + "小时前";
//        } else {
//            if (isTheSameYear(time)) {
//                // if(isTheSameWeak(time)){
//                return getDateStr(time, "MM.dd HH:mm");
//                // } else {
//                // return getDateStr(time, "MM/dd HH:mm");
//                // }
//            } else {
//                return getDateStr(time, "yyyy.MM.dd HH:mm");// "yyyy/MM/dd HH:mm");
//            }
//        }
    }


    private static boolean isTheSameYear(long time) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time);
        Calendar now = Calendar.getInstance();
        return cal.get(Calendar.YEAR) == now.get(Calendar.YEAR);
    }

    public static String getIntervalHours(long begin, long end) {
        if (end < begin)
            return "";
        long between = (end - begin) / 1000;// 除以1000是为了转换成秒
        long day = between / (24 * 3600);
        long hour = between % (24 * 3600) / 3600;
        long minute = between % 3600 / 60;
        long second = between % 60 / 60;
        return (day == 0 ? "" : day + "天 ") + (hour == 0 ? "" : hour + "小时 ")
                + (minute == 0 ? "" : minute + "分 ")
                + (second == 0 ? "" : second + "秒");
    }

    public static long getHours(long finishTime, long endTime) {
        long time = finishTime - endTime;
        if (time > 0) {
            return time / (3600 * 1000);
        }
        return 0;
    }

    public static boolean isSameTimeWithoutSecond(long time, long time2) {
        return getDateStr(time, FORMAT_YYMMDDHHMM).equals(
                getDateStr(time2, FORMAT_YYMMDDHHMM));
    }

    public static long getDurationMinutes(long begin, long end) {
        if (end < begin)
            return 0;
        long between = (end - begin) / 1000;// 除以1000是为了转换成秒
        long minutes = between / 60;
        return minutes;
    }

    public static String getDurationMinutes(long minutes) {
        if (minutes <= 0)
            return "";
        long day = minutes / (24 * 60);
        long hour = minutes % (24 * 60) / 60;
        long minute = minutes % 60;
        StringBuilder buf = new StringBuilder();
        buf.append((day == 0 ? "" : day + "天 "));
        buf.append((hour == 0 ? "" : hour + "小时 "));
        buf.append((minute == 0 ? "" : minute + "分 "));
        return buf.toString();
    }


    public static boolean isYestaday(long time) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time);
        cal.add(Calendar.DAY_OF_YEAR, 1);
        Calendar now = Calendar.getInstance();
        return cal.get(Calendar.YEAR) == now.get(Calendar.YEAR)
                && cal.get(Calendar.DAY_OF_YEAR) == now
                .get(Calendar.DAY_OF_YEAR)
                && cal.get(Calendar.MONTH) == now.get(Calendar.MONTH);
    }


    public static List<String> formatTransLaction(String createTime) {

        ArrayList<String> list = new ArrayList<>();
        if (createTime.length() == 14) {
            String yyyy = createTime.substring(0, 4);
            String MM = createTime.substring(4, 6);
            String dd = createTime.substring(6, 8);
            String hh = createTime.substring(8, 10);
            String mm = createTime.substring(10, 12);
            String ss = createTime.substring(12);
            list.add(yyyy + "-" + MM + "-" + dd);
            list.add(hh + ":" + mm + ":" + ss);

        } else {
            list.add("0000:00:00");
            list.add("00:00:00");
        }

        return list;
    }

    public static String formatTime(String end_time) {

        //  LogUtil.e("formatTime结束时间===" + end_time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");

        try {
            Date date = sdf.parse(end_time + "");
            SimpleDateFormat sp = new SimpleDateFormat("MM月dd日 hh:mm:ss");

            String format = sp.format(date);
            return format;

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "";
    }

    public static String formatTime(Long time) {

        long l = time * 1000;
        Date date1 = new Date(l);


        //  LogUtil.e("formatTime结束时间===" + end_time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd/hh:mm");

        String format = sdf.format(date1);
        return format;
//        try {
//            // Date date = sdf.parse(end_time + "");
//            SimpleDateFormat sp = new SimpleDateFormat("MM月dd日 hh:mm:ss");
//
//            String format = sp.format(date);
//            return format;
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

    }

    public static String isLastTime(String paramString) {
        try {
            long l = Long.valueOf(paramString).longValue() * 1000L - System.currentTimeMillis();
            if (l > 0L) {
                paramString = longTimeToDay(Long.valueOf(l));
                return paramString;
            }
        } catch (Exception e) {
        }
        return "";
    }

    public static String longTimeToDay(Long time) {


        long totalhour = time / ONE_HOUR;
        long totalMin = (time - totalhour * ONE_HOUR) / ONE_MINUTE;


        //总分分钟
//        long l1 = time.longValue() / 3600000L;
//        //小时
//        long l2 = (time.longValue() - 3600000L * l1) / 60000L;
//        //天
//        long l3 = (time.longValue() - 3600000L * l1 - 60000L * l2) / 1000L;
        StringBuffer paramLong = new StringBuffer();


        if (totalhour <= 0) {
            paramLong.append("00");
        } else if (totalhour < 10) {
            paramLong.append("0");
            paramLong.append(totalhour);
        } else {
            paramLong.append(totalhour);
        }

        paramLong.append(":");
        totalMin += 1;
        if (totalMin <= 0) {
            paramLong.append("01");
        } else if (totalMin < 10) {
            paramLong.append("0");
            paramLong.append(totalMin);
        } else {
            paramLong.append(totalMin);
        }


        return paramLong.toString();
    }


    public void timer() {


        Observable.timer(1000, TimeUnit.MICROSECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

}

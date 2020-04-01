package com.ant.modul.test.javaTest;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2020/3/30.
 * describe：
 */
public class SimpleClass {
    public boolean isTeenager(int age) {


        if (age < 15) {
            return true;
        }
        return false;
    }

    public int add(int a, int b) {
        return a + b;
    }

    public String getNameById(int id) {
        if (id == 1) {
            return "小明";
        } else if (id == 2) {
            return "小红";
        }
        return "";
    }

    public String formatViedoTime(String date, String format) {
        String sdf;
        try {
            Date date1 = new Date(Long.parseLong(date) * 1000);
            sdf = new SimpleDateFormat(format, Locale.getDefault()).format(date1);
        } catch (Exception e) {
            return "";
        }
        return sdf;
    }

    public void setMessage(String haha) {

        Log.e("msg", "msg==========  " + haha);


    }
}

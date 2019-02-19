package com.ant.utils.language;

import com.ant.base.BaseBean;

import java.util.Locale;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2018/10/23.
 * describe：语言基础类
 */
public class LanguageBean extends BaseBean {


    public LanguageBean(String language_name, Locale locale) {
        this.language_name = language_name;
        this.locale = locale;
    }

    private String language_name; //语言基础类
    private Locale locale;//系统与哈

    public LanguageBean() {
    }


    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }


    public String getLanguage_name() {
        return language_name;
    }

    public void setLanguage_name(String language_name) {
        this.language_name = language_name;
    }
}

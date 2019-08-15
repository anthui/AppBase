package com.ant.app_utils.language;

import com.ant.app_base.BaseBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2018/10/23.
 * describe：
 */
public class MyLanguageDataHelper extends BaseBean {
    private List<LanguageBean> languageBeans;

    public List<LanguageBean> getLanguageBeans() {
        return languageBeans;
    }

    protected static MyLanguageDataHelper myLanguageDataHelper = new MyLanguageDataHelper();

    private MyLanguageDataHelper() {
        languageBeans = new ArrayList<>();
        //languageBeans.add(new LanguageBean(R.string.str_system, Locale.CHINESE));
        //  languageBeans.add(new LanguageBean("自动", Locale.ENGLISH));
        languageBeans.add(new LanguageBean("English", Locale.ENGLISH));
        languageBeans.add(new LanguageBean("简体中文", Locale.CANADA));
    }

    public static MyLanguageDataHelper getInstance() {
        return myLanguageDataHelper;
    }


}

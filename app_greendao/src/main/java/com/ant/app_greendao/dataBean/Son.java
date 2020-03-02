package com.ant.app_greendao.dataBean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2020/2/24.
 * describe：
 */
@Entity
public class Son extends Parent {
    private String home;

    @Generated(hash = 1083174020)
    public Son(String home) {
        this.home = home;
    }

    @Generated(hash = 1259336981)
    public Son() {
    }

    public String getHome() {
        return this.home;
    }

    public void setHome(String home) {
        this.home = home;
    }
}

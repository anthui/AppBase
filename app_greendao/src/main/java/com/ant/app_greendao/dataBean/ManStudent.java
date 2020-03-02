package com.ant.app_greendao.dataBean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2020/2/21.
 * describe：
 */
@Entity
public class ManStudent extends Student {
    private String haha;

    @Generated(hash = 1523961862)
    public ManStudent(String haha) {
        this.haha = haha;
    }

    @Generated(hash = 1685020491)
    public ManStudent() {
    }

    public String getHaha() {
        return this.haha;
    }

    public void setHaha(String haha) {
        this.haha = haha;
    }


}

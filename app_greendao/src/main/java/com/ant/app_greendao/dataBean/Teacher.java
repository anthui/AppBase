package com.ant.app_greendao.dataBean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2020/2/20.
 * describe：
 */
@Entity
public class Teacher {

    @Id
    private Long teacherId;
    private String name;
    private String age;



}

package com.ant.app_greendao.dataBean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

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
    @Generated(hash = 1739493326)
    public Teacher(Long teacherId, String name, String age) {
        this.teacherId = teacherId;
        this.name = name;
        this.age = age;
    }
    @Generated(hash = 1630413260)
    public Teacher() {
    }
    public Long getTeacherId() {
        return this.teacherId;
    }
    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAge() {
        return this.age;
    }
    public void setAge(String age) {
        this.age = age;
    }



}

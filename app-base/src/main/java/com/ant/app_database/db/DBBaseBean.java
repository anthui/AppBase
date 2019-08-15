package com.ant.app_database.db;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2019/8/8.
 * describe：
 */
public abstract class DBBaseBean implements Serializable {

    //默认的id
    @DatabaseField(generatedId = true)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

package com.ant.app_greendao.dataBean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2019/12/30.
 * describe：
 */

@Entity
public  class Student {

    @Id
    private Long id;
    @Unique private
    int studentNo;

    private String teacherId;

    private int age;
    private int name;
    private int sex;
    private String address;
    private String schoolName;
    private String grade;
    @Generated(hash = 376677753)
    public Student(Long id, int studentNo, String teacherId, int age, int name,
            int sex, String address, String schoolName, String grade) {
        this.id = id;
        this.studentNo = studentNo;
        this.teacherId = teacherId;
        this.age = age;
        this.name = name;
        this.sex = sex;
        this.address = address;
        this.schoolName = schoolName;
        this.grade = grade;
    }
    @Generated(hash = 1556870573)
    public Student() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getStudentNo() {
        return this.studentNo;
    }
    public void setStudentNo(int studentNo) {
        this.studentNo = studentNo;
    }
    public String getTeacherId() {
        return this.teacherId;
    }
    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }
    public int getAge() {
        return this.age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public int getName() {
        return this.name;
    }
    public void setName(int name) {
        this.name = name;
    }
    public int getSex() {
        return this.sex;
    }
    public void setSex(int sex) {
        this.sex = sex;
    }
    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getSchoolName() {
        return this.schoolName;
    }
    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
    public String getGrade() {
        return this.grade;
    }
    public void setGrade(String grade) {
        this.grade = grade;
    }


}

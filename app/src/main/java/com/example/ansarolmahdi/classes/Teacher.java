package com.example.ansarolmahdi.classes;

import android.os.Parcelable;

public class Teacher extends Person implements Parcelable {
    private int teacherID;

    public Teacher(String fName, String lName, String eMail, String password, int teacherID) {
        super(fName, lName, eMail, password);
        this.teacherID = teacherID;
    }

    public Teacher() {
    }

    public int getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }
}

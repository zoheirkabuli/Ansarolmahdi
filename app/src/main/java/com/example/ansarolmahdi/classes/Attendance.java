package com.example.ansarolmahdi.classes;

import android.os.Parcel;
import android.os.Parcelable;

import java.sql.Time;


public class Attendance implements Parcelable {
    private int attendanceID,sessionID,studentID;
    private boolean isPresent;
    private String eTime,qTime;

    public Attendance(int sessionID, int studentID, boolean isPresent, String eTime, String qTime) {
        this.sessionID = sessionID;
        this.studentID = studentID;
        this.isPresent = isPresent;
        this.eTime = eTime;
        this.qTime = qTime;
    }

    public Attendance() {
    }


    protected Attendance(Parcel in) {
        attendanceID = in.readInt();
        sessionID = in.readInt();
        studentID = in.readInt();
        isPresent = in.readByte() != 0;
        eTime = in.readString();
        qTime = in.readString();
    }

    public static final Creator<Attendance> CREATOR = new Creator<Attendance>() {
        @Override
        public Attendance createFromParcel(Parcel in) {
            return new Attendance(in);
        }

        @Override
        public Attendance[] newArray(int size) {
            return new Attendance[size];
        }
    };

    public int getAttendanceID() {
        return attendanceID;
    }

    public void setAttendanceID(int attendanceID) {
        this.attendanceID = attendanceID;
    }

    public int getSessionID() {
        return sessionID;
    }

    public void setSessionID(int sessionID) {
        this.sessionID = sessionID;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public boolean isPresent() {
        return isPresent;
    }

    public void setPresent(boolean present) {
        isPresent = present;
    }

    public String geteTime() {
        return eTime;
    }

    public void seteTime(String eTime) {
        this.eTime = eTime;
    }

    public String getqTime() {
        return qTime;
    }

    public void setqTime(String qTime) {
        this.qTime = qTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(attendanceID);
        parcel.writeInt(sessionID);
        parcel.writeInt(studentID);
        parcel.writeByte((byte) (isPresent ? 1 : 0));
        parcel.writeString(eTime);
        parcel.writeString(qTime);
    }
}

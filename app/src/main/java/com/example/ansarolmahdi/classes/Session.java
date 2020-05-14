package com.example.ansarolmahdi.classes;

import android.os.Parcel;
import android.os.Parcelable;


public class Session implements Parcelable {
    private int sessionID,courseID,sessionNum;
    private String weekDay;
    private String sessionDate;

    public Session(int sessionID, int courseID, int sessionNum, String weekDay, String sessionDate) {
        this.sessionID = sessionID;
        this.courseID = courseID;
        this.sessionNum = sessionNum;
        this.weekDay = weekDay;
        this.sessionDate = sessionDate;
    }

    public Session() {

    }

    protected Session(Parcel in) {
        sessionID = in.readInt();
        courseID = in.readInt();
        sessionNum = in.readInt();
        weekDay = in.readString();
        sessionDate = in.readString();
    }

    public static final Creator<Session> CREATOR = new Creator<Session>() {
        @Override
        public Session createFromParcel(Parcel in) {
            return new Session(in);
        }

        @Override
        public Session[] newArray(int size) {
            return new Session[size];
        }
    };

    public int getSessionID() {
        return sessionID;
    }

    public void setSessionID(int sessionID) {
        this.sessionID = sessionID;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public int getSessionNum() {
        return sessionNum;
    }

    public void setSessionNum(int sessionNum) {
        this.sessionNum = sessionNum;
    }

    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }

    public String getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(String sessionDate) {
        this.sessionDate = sessionDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(sessionID);
        parcel.writeInt(courseID);
        parcel.writeInt(sessionNum);
        parcel.writeString(weekDay);
        parcel.writeString(sessionDate);
    }
}

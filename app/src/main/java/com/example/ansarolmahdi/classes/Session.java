package com.example.ansarolmahdi.classes;

import java.util.ArrayList;

public class Session {
    private int sessionID,courseID,sessionNum;
    private String weekDay;
    private String string;
    private ArrayList<String> sessions;

    public Session(int courseID, int sessionNum, java.lang.String weekDay, String string) {
        this.courseID = courseID;
        this.sessionNum = sessionNum;
        this.weekDay = weekDay;
        this.string = string;
    }

    public Session() {
    }

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

    public java.lang.String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(java.lang.String weekDay) {
        this.weekDay = weekDay;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public ArrayList<String> getSessions() {
        return sessions;
    }

    public void setSessions(ArrayList<String> sessions) {
        this.sessions = sessions;
    }
}

package com.example.ansarolmahdi.classes;

public class Session {
    private int sessionID,courseID,sessionNum;
    private String weekDay;
    private Date date;

    public Session(int sessionID, int courseID, int sessionNum, String weekDay, Date date) {
        this.sessionID = sessionID;
        this.courseID = courseID;
        this.sessionNum = sessionNum;
        this.weekDay = weekDay;
        this.date = date;
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

    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

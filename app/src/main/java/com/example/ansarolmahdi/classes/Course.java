package com.example.ansarolmahdi.classes;

public class Course {
    private int courseID,userID,numberOfSessions,cost;
    private String title;
    private Date startDate;
    private Time time;

    public Course(int userID, int numberOfSessions, int cost, String title, Date startDate, Time time) {
        this.userID = userID;
        this.numberOfSessions = numberOfSessions;
        this.cost = cost;
        this.title = title;
        this.startDate = startDate;
        this.time = time;
    }

    public Course() {
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getNumberOfSessions() {
        return numberOfSessions;
    }

    public void setNumberOfSessions(int numberOfSessions) {
        this.numberOfSessions = numberOfSessions;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}

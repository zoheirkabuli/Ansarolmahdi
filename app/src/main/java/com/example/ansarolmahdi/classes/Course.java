package com.example.ansarolmahdi.classes;

import android.os.Parcel;
import android.os.Parcelable;

import java.sql.Time;
import java.util.ArrayList;

public class Course implements Parcelable {
    private int courseID,userID,numberOfSessions,cost;
    private String title;
    private String startDate,time;
    private ArrayList<Session> sessions;



    public Course(int userID, int numberOfSessions, int cost, java.lang.String title, String startDate,String time,
                  ArrayList<Session> sessions) {
        this.userID = userID;
        this.numberOfSessions = numberOfSessions;
        this.cost = cost;
        this.title = title;
        this.startDate = startDate;
        this.time = time;
        this.sessions = sessions;
    }


    public Course() {
    }


    protected Course(Parcel in) {
        courseID = in.readInt();
        userID = in.readInt();
        numberOfSessions = in.readInt();
        cost = in.readInt();
        title = in.readString();
        startDate = in.readString();
        time = in.readString();
        this.sessions = in.readArrayList(null);
    }

    public static final Creator<Course> CREATOR = new Creator<Course>() {
        @Override
        public Course createFromParcel(Parcel in) {
            return new Course(in);
        }

        @Override
        public Course[] newArray(int size) {
            return new Course[size];
        }
    };

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

    public void setTitle(java.lang.String title) {
        this.title = title;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getCourseID() {
        return courseID;
    }

    public ArrayList<Session> getSessions() {
        return sessions;
    }

    public void setSessions(ArrayList<Session> sessions) {
        this.sessions = sessions;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(courseID);
        parcel.writeInt(userID);
        parcel.writeInt(numberOfSessions);
        parcel.writeInt(cost);
        parcel.writeString(title);
        parcel.writeString(startDate);
        parcel.writeString(time);
        parcel.writeList(sessions);
    }
}

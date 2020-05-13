package com.example.ansarolmahdi.classes;

import android.os.Parcel;
import android.os.Parcelable;

import java.sql.Time;

public class Course implements Parcelable {
    private int courseID,userID,numberOfSessions,cost;
    private String title;
    private String startDate;
    private Time time;



    public Course(int userID, int numberOfSessions, int cost, java.lang.String title, String startDate,Time time) {
        this.userID = userID;
        this.numberOfSessions = numberOfSessions;
        this.cost = cost;
        this.title = title;
        this.startDate = startDate;
        this.time = time;
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

    public java.lang.String getTitle() {
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
    }

//    public Time getTime() {
//        return time;
//    }
//
//    public void setTime(Time time) {
//        this.time = time;
//    }




}

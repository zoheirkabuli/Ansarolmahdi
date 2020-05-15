package com.example.ansarolmahdi.classes;

import android.os.Parcel;
import android.os.Parcelable;

public class HomeWork implements Parcelable {
    private int homeworkID,sessionID;
    private String text;
    private String deadline;

    public HomeWork(int sessionID, java.lang.String text, String deadline) {
        this.sessionID = sessionID;
        this.text = text;
        this.deadline = deadline;
    }

    public HomeWork() {
    }

    protected HomeWork(Parcel in) {
        homeworkID = in.readInt();
        sessionID = in.readInt();
        text = in.readString();
        deadline = in.readString();
    }

    public static final Creator<HomeWork> CREATOR = new Creator<HomeWork>() {
        @Override
        public HomeWork createFromParcel(Parcel in) {
            return new HomeWork(in);
        }

        @Override
        public HomeWork[] newArray(int size) {
            return new HomeWork[size];
        }
    };

    public int getHomeworkID() {
        return homeworkID;
    }

    public void setHomeworkID(int homeworkID) {
        this.homeworkID = homeworkID;
    }

    public int getSessionID() {
        return sessionID;
    }

    public void setSessionID(int sessionID) {
        this.sessionID = sessionID;
    }

    public java.lang.String getText() {
        return text;
    }

    public void setText(java.lang.String text) {
        this.text = text;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(homeworkID);
        parcel.writeInt(sessionID);
        parcel.writeString(text);
        parcel.writeString(deadline);
    }
}

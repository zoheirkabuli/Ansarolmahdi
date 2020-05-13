package com.example.ansarolmahdi.classes;

import android.os.Parcel;
import android.os.Parcelable;

import java.sql.Time;


public class Attendance implements Parcelable {
    private int attendanceID,sessionID,studentID;
    private boolean isPresent;
    private Time eTime,qTime;

    public Attendance(int sessionID, int studentID, boolean isPresent, Time eTime, Time qTime) {
        this.sessionID = sessionID;
        this.studentID = studentID;
        this.isPresent = isPresent;
        this.eTime = eTime;
        this.qTime = qTime;
    }

    protected Attendance(Parcel in) {
        attendanceID = in.readInt();
        sessionID = in.readInt();
        studentID = in.readInt();
        isPresent = in.readByte() != 0;
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
    }
}

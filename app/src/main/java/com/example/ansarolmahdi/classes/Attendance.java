package com.example.ansarolmahdi.classes;

public class Attendance {
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
}

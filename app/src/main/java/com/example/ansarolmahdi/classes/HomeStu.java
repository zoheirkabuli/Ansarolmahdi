package com.example.ansarolmahdi.classes;

public class HomeStu {
    private int homestuID,studentID,homeworkID;

    public HomeStu(int studentID, int homeworkID) {
        this.studentID = studentID;
        this.homeworkID = homeworkID;
    }

    public int getHomestuID() {
        return homestuID;
    }

    public int getStudentID() {
        return studentID;
    }

    public int getHomeworkID() {
        return homeworkID;
    }
}

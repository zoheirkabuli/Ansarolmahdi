package com.example.ansarolmahdi.classes;

public class StuCourse {
    private int stucourseID,studentID,courseID;

    public StuCourse(int studentID, int courseID) {
        this.studentID = studentID;
        this.courseID = courseID;
    }

    public int getStucourseID() {
        return stucourseID;
    }

    public int getStudentID() {
        return studentID;
    }

    public int getCourseID() {
        return courseID;
    }
}

package com.example.ansarolmahdi.classes;

import java.sql.Time;

public class Exam {
    private int examID,courseID;
    private String string;
    private Time time;
    private String title;

    public Exam(int courseID, String string, Time time, java.lang.String title) {
        this.courseID = courseID;
        this.string = string;
        this.time = time;
        this.title = title;
    }

    public int getExamID() {
        return examID;
    }

    public int getCourseID() {
        return courseID;
    }

    public String getString() {
        return string;
    }

    public Time getTime() {
        return time;
    }

    public java.lang.String getTitle() {
        return title;
    }
}

package com.example.ansarolmahdi.classes;

public class Exam {
    private int examID,courseID;
    private Date date;
    private Time time;
    private String title;

    public Exam(int examID, int courseID, Date date, Time time, String title) {
        this.examID = examID;
        this.courseID = courseID;
        this.date = date;
        this.time = time;
        this.title = title;
    }

    public int getExamID() {
        return examID;
    }

    public int getCourseID() {
        return courseID;
    }

    public Date getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }

    public String getTitle() {
        return title;
    }
}

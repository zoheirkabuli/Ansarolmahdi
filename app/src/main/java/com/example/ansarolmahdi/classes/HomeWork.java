package com.example.ansarolmahdi.classes;

public class HomeWork {
    private int homeworkID,sessionID;
    private java.lang.String text;
    private String deadline;

    public HomeWork(int sessionID, java.lang.String text, String deadline) {
        this.sessionID = sessionID;
        this.text = text;
        this.deadline = deadline;
    }

    public HomeWork() {
    }

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
}

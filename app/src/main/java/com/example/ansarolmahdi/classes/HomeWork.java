package com.example.ansarolmahdi.classes;

public class HomeWork {
    private int homeworkID,sessionID;
    private String text;
    private Date deadline;

    public HomeWork(int homeworkID, int sessionID, String text, Date deadline) {
        this.homeworkID = homeworkID;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }
}

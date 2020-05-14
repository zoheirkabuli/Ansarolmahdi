package com.example.ansarolmahdi.classes;

public class Student extends Person {
    private int studentID,parentID;

    public Student(String fName, String lName, String eMail, String password, int parentID) {
        super(fName, lName, eMail, password);
        this.parentID = parentID;
    }

    public Student() {
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public int getParentID() {
        return parentID;
    }

    public void setParentID(int parentID) {
        this.parentID = parentID;
    }
}

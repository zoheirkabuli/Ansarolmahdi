package com.example.ansarolmahdi.classes;

public class Parent extends Person {
    private int parentID;
    private String address,landline;

    public Parent(String fName, String lName, String eMail, String password, String address, String landline) {
        super(fName, lName, eMail, password);
        this.address = address;
        this.landline = landline;
    }

    public int getParentID() {
        return parentID;
    }

    public void setParentID(int parentID) {
        this.parentID = parentID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLandline() {
        return landline;
    }

    public void setLandline(String landline) {
        this.landline = landline;
    }
}

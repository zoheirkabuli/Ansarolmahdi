package com.example.ansarolmahdi.classes;

public class User extends Person {
    private int userID,roleID;

    public User(String fName, String lName, String eMail, String password, int roleID) {
        super(fName, lName, eMail, password);
        this.roleID = roleID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }
}

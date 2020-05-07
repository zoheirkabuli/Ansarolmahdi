package com.example.ansarolmahdi.classes;

public class Role {
    private int roleID;
    private String title;

    public Role(int roleID, String title) {
        this.roleID = roleID;
        this.title = title;
    }

    public int getRoleID() {
        return roleID;
    }

    public String getTitle() {
        return title;
    }
}

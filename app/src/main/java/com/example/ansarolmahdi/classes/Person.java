package com.example.ansarolmahdi.classes;

import android.os.Parcel;
import android.os.Parcelable;

public class Person implements Parcelable {
    private int personID;
    private int access;
    private String fName;
    private String lName;
    private String eMail;
    private String phone;
    private String password;
    private String response;

    public Person(String fName, String lName, String eMail, String password) {
        this.fName = fName;
        this.lName = lName;
        this.eMail = eMail;
        this.password = password;
    }


    public Person() {
    }

    protected Person(Parcel in) {
        personID = in.readInt();
        access = in.readInt();
        fName = in.readString();
        lName = in.readString();
        eMail = in.readString();
        phone = in.readString();
        password = in.readString();
        response = in.readString();
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    public int getPersonID() {
        return personID;
    }

    public void setPersonID(int personID) {
        this.personID = personID;
    }

    public int getAccess() {
        return access;
    }

    public void setAccess(int access) {
        this.access = access;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(personID);
        parcel.writeInt(access);
        parcel.writeString(fName);
        parcel.writeString(lName);
        parcel.writeString(eMail);
        parcel.writeString(phone);
        parcel.writeString(password);
        parcel.writeString(response);
    }
}

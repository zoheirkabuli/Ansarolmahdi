package com.example.ansarolmahdi.classes;

public class Time {
    private int mHour,mMinute;

    public Time(int mHour, int mMinute) {
        this.mHour = mHour;
        this.mMinute = mMinute;
    }

    public int getmHour() {
        return mHour;
    }

    public int getmMinute() {
        return mMinute;
    }

    @Override
    public String toString() {
        return mHour + ":" + mMinute;
    }
}

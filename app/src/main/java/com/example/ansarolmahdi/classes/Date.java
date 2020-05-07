package com.example.ansarolmahdi.classes;

public class Date {
    private int mDay,mMonth,mYear;

    public Date(int mDay, int mMonth, int mYear) {
        this.mDay = mDay;
        this.mMonth = mMonth;
        this.mYear = mYear;
    }

    public int getmDay() {
        return mDay;
    }

    public int getmMonth() {
        return mMonth;
    }

    public int getmYear() {
        return mYear;
    }

    @Override
    public String toString() {
        return mYear + "/" + mMonth + "/" + mDay;
    }
}

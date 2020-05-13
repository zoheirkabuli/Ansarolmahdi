package com.example.ansarolmahdi.classes;

import java.util.Date;

public class MyDate extends Date {

//    Month Should be month - 1

    @Override
    public void setYear(int year) {
        super.setYear(year - 1900);
    }


}

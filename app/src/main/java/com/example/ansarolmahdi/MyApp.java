package com.example.ansarolmahdi;

import android.app.Application;

import androidx.core.graphics.TypefaceCompatUtil;

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "fonts/iransansmobilefanum.ttf");

    }
}

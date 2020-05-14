package com.example.ansarolmahdi;

import android.app.Application;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class Font extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/iransansmobilefanum.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

    }

}

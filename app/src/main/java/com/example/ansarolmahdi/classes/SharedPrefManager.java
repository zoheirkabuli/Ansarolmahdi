package com.example.ansarolmahdi.classes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.ansarolmahdi.MainActivity;

public class SharedPrefManager {

        //the constants
        private static final String SHARED_PREF_NAME = "simplifiedcodingsharedpref";
        private static final String KEY_PASSWORD = "keypassword";
        private static final String KEY_EMAIL = "keyemail";

        private static SharedPrefManager mInstance;
        private static Context mCtx;

        private SharedPrefManager(Context context) {
            mCtx = context;
        }

        public static synchronized SharedPrefManager getInstance(Context context) {
            if (mInstance == null) {
                mInstance = new SharedPrefManager(context);
            }
            return mInstance;
        }

        //method to let the user login
        //this method will store the user data in shared preferences
        public void userLogin(Person user) {
            SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(KEY_EMAIL, user.geteMail());
            editor.putString(KEY_PASSWORD, user.getPassword());
            editor.apply();
        }

        //this method will check whether user is already logged in or not
        public boolean isLoggedIn() {
            SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
            return sharedPreferences.getString(KEY_PASSWORD, null) != null;
        }

        //this method will give the logged in user
        public Person getPerson() {
            SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
            Person person = new Person();
            person.seteMail(sharedPreferences.getString(KEY_EMAIL,null));
            person.setPassword(sharedPreferences.getString(KEY_PASSWORD, null));
            return person;
        }

        //this method will logout the user
        public void logout() {
            SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();
            mCtx.startActivity(new Intent(mCtx, MainActivity.class));
        }
}

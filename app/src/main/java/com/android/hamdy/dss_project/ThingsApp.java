package com.android.hamdy.dss_project;

import android.app.Application;

import com.android.hamdy.dss_project.Utilities.SharedPref;

/**
 * Created by hamdy on 16/05/17.
 */

public class ThingsApp extends Application {


    public static final String PREF_KEY = "pref_thing_app";
    public User user;

    @Override
    public void onCreate() {
        super.onCreate();

        user = new User();

        String id =SharedPref.getString(getApplicationContext() , "pref_user_id" );
        String name =  SharedPref.getString(getApplicationContext() , "pref_user_name");
        String email = SharedPref.getString(getApplicationContext() , "pref_user_email");


        user.id = id;
        user.name = name;
        user.email = email;
    }
}




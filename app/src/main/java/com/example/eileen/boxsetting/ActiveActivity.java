package com.example.eileen.boxsetting;

import android.support.v7.app.AppCompatActivity;

public class ActiveActivity {

    private static AppCompatActivity activity;

    public static void setActiveActivity(AppCompatActivity appCompatActivity){
        activity = appCompatActivity;
    }

    public static AppCompatActivity getActiveActivity(){
        return activity;
    }
}

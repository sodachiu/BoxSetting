package com.example.eileen.boxsetting;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;

public class FragmentView {
    private static final String TAG = "FragmentView";
    private FragmentManager fragmentManager;
    private AppCompatActivity appCA;


    public FragmentView(AppCompatActivity appCompatActivity){
        appCA = appCompatActivity;
        fragmentManager = appCA.getSupportFragmentManager();
    }



    public void addFragment(Fragment fragment, String tag){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.content, fragment, tag);
        transaction.addToBackStack(tag);
        transaction.commit();

    }



}

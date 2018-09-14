package com.example.eileen.boxsetting;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

public class FragmentView {
    private FragmentManager fragmentManager;
    private AppCompatActivity appAc;

    public FragmentView(AppCompatActivity appCompatActivity){
        appAc = appCompatActivity;
        fragmentManager = appAc.getSupportFragmentManager();

    }

    protected String fragmentLoseFocus(ViewGroup viewGroup){

        boolean isRemoveSuccess = false;
         if( !viewGroup.hasFocus()){
             isRemoveSuccess = removeFragment();
         }

         if (isRemoveSuccess){
             return "fragmentLoseFocus success";
         }else {
             return "fragmentLoseFocus fail";
         }
    }


    protected void addFragment(Fragment fragment, String tag){

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.content, fragment, tag);
        transaction.addToBackStack(tag);
        transaction.commit();
    }

    protected boolean removeFragment(){
        boolean isClear = false;

        for (int i = 0; i < fragmentManager.getBackStackEntryCount(); i++){
            isClear = fragmentManager.popBackStackImmediate();
        }

        return isClear;
    }
}

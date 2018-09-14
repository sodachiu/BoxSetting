package com.example.eileen.boxsetting;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;


public class MainActivity extends AppCompatActivity {
    public AppCompatActivity appCompatActivity = MainActivity.this;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout menu = (LinearLayout)findViewById(R.id.menu);
        MenuView menuView = new MenuView(menu, MainActivity.this);

    }

}

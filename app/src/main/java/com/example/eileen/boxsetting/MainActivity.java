package com.example.eileen.boxsetting;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnKeyListener{
    public AppCompatActivity appCompatActivity = MainActivity.this;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView myInfo = (TextView) findViewById(R.id.my_info);
        myInfo.setFocusable(true);
        myInfo.setBackgroundResource(R.drawable.menu_focus_selector);
//        ActiveActivity.setActiveActivity(MainActivity.this);
        myInfo.setOnKeyListener(this);

    }



    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN){
            switch (keyCode){
                case KeyEvent.KEYCODE_DPAD_DOWN:
                    Intent intent = new Intent(MainActivity.this, NetSettingActivity.class);
                    startActivity(intent);
                    break;
                default:
                    break;
            }
        }

        return false;
    }

}

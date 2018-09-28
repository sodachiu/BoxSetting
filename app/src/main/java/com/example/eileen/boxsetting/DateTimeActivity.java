package com.example.eileen.boxsetting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DateTimeActivity extends AppCompatActivity
implements View.OnKeyListener, View.OnClickListener{

    private TextView dateTime;
    private LinearLayout timeFormat;
    private LinearLayout dateFormat;
    private TextView checkbox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_time);
        ActiveActivity.setActiveActivity(this);

        dateTime = (TextView) findViewById(R.id.date_time);
        timeFormat = (LinearLayout) findViewById(R.id.time_format);
        dateFormat = (LinearLayout) findViewById(R.id.date_format);
        checkbox = (TextView) findViewById(R.id.image_time_format);

        dateTime.setFocusable(true);
        dateTime.setBackgroundResource(R.drawable.menu_focus_selector);
        dateTime.setOnKeyListener(this);

        timeFormat.setOnClickListener(this);
        dateFormat.setOnClickListener(this);
    }

    protected void onResume(){
        super.onResume();
        timeFormat.setFocusable(true);
        dateFormat.setFocusable(true);
    }

    @Override
    public void onClick(View v){
        Intent intent;
        switch (v.getId()){
            case R.id.time_format:
                String status = (String) timeFormat.getContentDescription();
                ImageToggle.toggle(status, timeFormat,checkbox);
                break;
            case R.id.date_format:
                intent = new Intent(DateTimeActivity.this, DateFormatActivity.class);
                startActivity(intent);
                break;
            default:
                break;

        }
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event){

        if (event.getAction() == KeyEvent.ACTION_DOWN){
            Intent intent;
            switch (keyCode){
                case KeyEvent.KEYCODE_DPAD_DOWN:
                    intent = new Intent(DateTimeActivity.this, DisplayActivity.class);
                    startActivity(intent);
                    break;
                case KeyEvent.KEYCODE_DPAD_UP:
                    timeFormat.setFocusable(false);
                    dateFormat.setFocusable(false);
                    intent = new Intent(DateTimeActivity.this, NetInfoActivity.class);
                    startActivity(intent);
                    break;
                default:
                    break;
            }
        }
        return false;
    }


}

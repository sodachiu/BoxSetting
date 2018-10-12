package com.example.eileen.boxsetting;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextClock;
import android.widget.TextView;

public class DateTimeActivity extends AppCompatActivity
implements View.OnKeyListener, View.OnClickListener{

    private static final String TAG = "DateTimeActivity";
    private TextView dateTime;
    private LinearLayout timeFormat;
    private LinearLayout dateFormat;
    private TextView checkbox24Hours;
    private TextClock tcTime;
    private TextClock tcDate;
    private SharedPreferences pref;
    private Boolean timeFormatIsChecked;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_time);
        dateTime = (TextView) findViewById(R.id.date_time);
        timeFormat = (LinearLayout) findViewById(R.id.time_format);
        dateFormat = (LinearLayout) findViewById(R.id.date_format);
        checkbox24Hours = (TextView) findViewById(R.id.image_switch_24hours);
        tcTime = (TextClock) findViewById(R.id.tc_time_format);
        tcDate = (TextClock) findViewById(R.id.tc_date_format);

        dateTime.setFocusable(true);
        dateTime.setBackgroundResource(R.drawable.menu_focus_selector);
        dateTime.setOnKeyListener(this);

        timeFormat.setOnClickListener(this);
        dateFormat.setOnClickListener(this);
        checkbox24Hours.setOnClickListener(this);
    }

    @Override
    protected void onResume(){
        super.onResume();
        timeFormat.setFocusable(true);
        dateFormat.setFocusable(true);
        pref = getSharedPreferences("data", MODE_PRIVATE);
//        timeFormatIsChecked = pref.getBoolean("time_format_is_24", false);
        Log.d(TAG, "onResume: " + tcTime.getFormat12Hour());
        Log.d(TAG, "onResume: " + tcTime.getFormat24Hour());
        Log.d(TAG, "onResume: " + tcTime.getTimeZone());
        /*if (timeFormatIsChecked){
            tcTime.setFormat24Hour("H:mm");
        }else{
            tcTime.setFormat12Hour("aa hh:mm");
        }*/
        Log.d(TAG, "onResume: " + tcTime.is24HourModeEnabled());

    }

    @Override
    protected void onPause(){
        super.onPause();
        /*SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("time_format_is_checked", checkbox24Hours.isChecked());
        editor.apply();*/
    }



    @Override
    public void onClick(View v){
        Intent intent;
        switch (v.getId()){
            case R.id.time_format:
                break;
            case R.id.date_format:
                intent = new Intent(DateTimeActivity.this, DateFormatActivity.class);
                startActivity(intent);
                break;
           /* case R.id.checkbox_switch_24hours:
                Log.d(TAG, "onClick: " + checkbox24Hours.isClickable());*/
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

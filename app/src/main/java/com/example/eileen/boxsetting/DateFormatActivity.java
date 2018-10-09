package com.example.eileen.boxsetting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextClock;
import android.widget.TextView;

public class DateFormatActivity extends AppCompatActivity {
    private TextView dateTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_format);
        dateTime = (TextView) findViewById(R.id.date_time);
        dateTime.setBackgroundResource(R.drawable.menu_item_select);
    }
}

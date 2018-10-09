package com.example.eileen.boxsetting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ClearAllActivity extends AppCompatActivity
        implements View.OnClickListener{

    private TextView advanced;
    private Button clearAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clear_all);
        advanced = (TextView) findViewById(R.id.advanced);
        clearAll = (Button) findViewById(R.id.button_clear_all);
        advanced.setBackgroundResource(R.drawable.menu_item_select);

        clearAll.setOnClickListener(this);
    }


    @Override
    public void onClick(View v){
        Toast.makeText(ClearAllActivity.this,"清理完成", Toast.LENGTH_SHORT).show();
    }
}

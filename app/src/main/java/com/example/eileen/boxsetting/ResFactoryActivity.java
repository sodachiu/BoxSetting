package com.example.eileen.boxsetting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ResFactoryActivity extends AppCompatActivity
        implements View.OnClickListener{

    private static final int RES_FACTORY = 99;
    private TextView resFactory;
    private EditText password;
    private Button confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res_factory);
        resFactory = (TextView) findViewById(R.id.res_factory);
        password = (EditText) findViewById(R.id.res_factory_pwd);
        confirm = (Button) findViewById(R.id.button_res_factory);

        resFactory.setFocusable(true);
        resFactory.setBackgroundResource(R.drawable.menu_focus_selector);
        confirm.setOnClickListener(this);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        ReturnDataUtil.disposeOfData(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View v){
        Intent intent = new Intent(ResFactoryActivity.this, ResFactoryDialogActivity.class);
        startActivityForResult(intent, RES_FACTORY);
    }
}

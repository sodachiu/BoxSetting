package com.example.eileen.boxsetting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.security.Key;

public class AdvancedActivity extends AppCompatActivity
        implements View.OnClickListener, View.OnKeyListener{

    private static final String TAG = "AdvancedActivity";
    private TextView advanced;
    private Button confirm;
    private EditText password;
    private TextView promptInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced);
        advanced = (TextView) findViewById(R.id.advanced);
        confirm = (Button) findViewById(R.id.button_advanced);
        password = (EditText) findViewById(R.id.advanced_pwd);
        promptInfo = (TextView) findViewById(R.id.advanced_wrong_pwd_text);

        advanced.setFocusable(true);
        advanced.setBackgroundResource(R.drawable.menu_focus_selector);

        advanced.setOnKeyListener(this);
        confirm.setOnClickListener(this);

    }


    @Override
    protected void onResume(){
        super.onResume();
        password.setFocusable(true);
        confirm.setFocusable(true);
        promptInfo.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onClick(View v){
        String pwdText = password.getText().toString();
        Log.d(TAG, "onClick: " + pwdText);

        // 拿到系统密码，与之对比
        if (pwdText.equals("10086") ){
            Intent intent = new Intent(AdvancedActivity.this, AdvancedItemActivity.class);
            startActivity(intent);

        }else {
            promptInfo.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event){

        if (event.getAction() == KeyEvent.ACTION_DOWN){

            Intent intent = new Intent();
            switch (keyCode){
                case KeyEvent.KEYCODE_DPAD_DOWN:
                    confirm.setFocusable(false);
                    intent = new Intent(AdvancedActivity.this, ResFactoryActivity.class);
                    startActivity(intent);
                    break;
                case KeyEvent.KEYCODE_DPAD_UP:
                    password.setFocusable(false);
                    intent = new Intent(AdvancedActivity.this, StoreInfoActivity.class);
                    startActivity(intent);
                    break;
                default:
                    break;
            }
        }
        return false;
    }

}

package com.example.eileen.boxsetting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class NetInfoActivity extends AppCompatActivity implements View.OnKeyListener{
    private TextView netInfo;
    private EditText mainDns;
    private EditText backupDns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_info);

        netInfo = (TextView) findViewById(R.id.net_info);
        mainDns = (EditText) findViewById(R.id.main_dns);
        backupDns = (EditText) findViewById(R.id.backup_dns);

        netInfo.setFocusable(true);
        netInfo.setBackgroundResource(R.drawable.menu_focus_selector);
        netInfo.setOnKeyListener(this);
    }

    protected void onResume(){
        super.onResume();
        mainDns.setFocusable(true);
        backupDns.setFocusable(true);
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event){

        if (event.getAction() == KeyEvent.ACTION_DOWN){
            Intent intent;
            switch (keyCode){
                case KeyEvent.KEYCODE_DPAD_DOWN:
                    mainDns.setFocusable(false);
                    backupDns.setFocusable(false);
                    intent = new Intent(NetInfoActivity.this, DateTimeActivity.class);
                    startActivity(intent);
                    break;
                case KeyEvent.KEYCODE_DPAD_UP:
                    intent = new Intent(NetInfoActivity.this, NetSettingActivity.class);
                    startActivity(intent);
                    break;
                default:
                    break;

            }
        }

        return false;
    }
}

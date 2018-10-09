package com.example.eileen.boxsetting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class StaticIpActivity extends AppCompatActivity {

    private TextView netSetting;
    private Button confirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_static_ip);
        netSetting = (TextView) findViewById(R.id.net_setting);
        confirm = (Button) findViewById(R.id.static_ip_confirm);
        netSetting.setBackgroundResource(R.drawable.menu_item_select);
        confirm.requestFocus();
    }
}

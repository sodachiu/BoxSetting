package com.example.eileen.boxsetting;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class  BluetoothSettingActivity extends AppCompatActivity {
    private TextView netSetting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bluetooth_activity);
        netSetting = (TextView) findViewById(R.id.net_setting);
        netSetting.setBackgroundResource(R.drawable.menu_item_select);

    }

}

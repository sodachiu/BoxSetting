package com.example.eileen.boxsetting;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import android.net.pppoe.PppoeManager;
import android.net.ethernet.EthernetManager;


public class PPPoEActivity extends AppCompatActivity {

    private TextView netSetting;
    private Button confirm;
    public static final String ETHERNET_SERVICE = "ethernet";
    public static final String PPPOE_SERVICE = "pppoe";
    public Context context = getBaseContext();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pppoe);
        netSetting = (TextView) findViewById(R.id.net_setting);
        confirm = (Button) findViewById(R.id.pppoe_confirm);
        netSetting.setBackgroundResource(R.drawable.menu_item_select);
        confirm.requestFocus();

    }
}

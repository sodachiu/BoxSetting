package com.example.eileen.boxsetting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class WireSettingActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView netSetting;
    private LinearLayout pppoe;
    private LinearLayout dhcp;
    private LinearLayout staticIp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.ethernet_activity);
        netSetting = (TextView) findViewById(R.id.net_setting);
        pppoe = (LinearLayout) findViewById(R.id.pppoe_container);
        dhcp = (LinearLayout) findViewById(R.id.dhcp_container);
        staticIp = (LinearLayout) findViewById(R.id.static_ip_container);

        netSetting.setBackgroundResource(R.drawable.menu_item_select);
        pppoe.setOnClickListener(this);
        dhcp.setOnClickListener(this);
        staticIp.setOnClickListener(this);
    }

    public void onClick(View v){
        Intent intent;
        switch (v.getId()){
            case R.id.pppoe_container:
                intent = new Intent(WireSettingActivity.this, PPPoEActivity.class);
                startActivity(intent);
                break;
            case R.id.dhcp_container:
                //这里做另外的点击事件处理，即点击就尝试连接有线
                break;
            case R.id.static_ip_container:
                intent = new Intent(WireSettingActivity.this, StaticIpActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}

package com.example.eileen.boxsetting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NetSettingActivity extends AppCompatActivity
        implements View.OnClickListener, View.OnKeyListener{

    private static final String TAG = "NetSettingActivity";
    private TextView netSetting;
    private LinearLayout setNet;
    private LinearLayout setBluetooth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_setting);

        netSetting = (TextView) findViewById(R.id.net_setting);
        setNet = (LinearLayout) findViewById(R.id.set_net);
        setBluetooth = (LinearLayout) findViewById(R.id.set_bluetooth);

        netSetting.setFocusable(true);
        netSetting.setBackgroundResource(R.drawable.menu_focus_selector);
        setNet.setOnClickListener(this);
        setBluetooth.setOnClickListener(this);
        netSetting.setOnKeyListener(this);

        //測試了一下NetworkInformation類是否可用
        /*NetworkInformation information = new NetworkInformation(getApplicationContext());
        Log.d(TAG, "onCreate: " + information.getIpAddress());*/


    }

    @Override
    protected void onResume() {
        super.onResume();
        setNet.setFocusable(true);
        setBluetooth.setFocusable(true);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.set_net:
                intent = new Intent(NetSettingActivity.this, WireSettingActivity.class);
                startActivity(intent);
                break;
            case R.id.set_bluetooth:
                intent = new Intent(NetSettingActivity.this, BluetoothSettingActivity.class);
                startActivity(intent);
                break;

            default:
                break;
        }
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event){
        if (event.getAction() == KeyEvent.ACTION_DOWN){

            switch (keyCode){
                case KeyEvent.KEYCODE_DPAD_DOWN:
                    setNet.setFocusable(false);
                    setBluetooth.setFocusable(false);
                    Intent intentDown = new Intent(NetSettingActivity.this, NetInfoActivity.class);
                    NetSettingActivity.this.startActivity(intentDown);
                    break;

                case KeyEvent.KEYCODE_DPAD_UP:
                    Intent intentUp = new Intent(NetSettingActivity.this, MainActivity.class);
                    NetSettingActivity.this.startActivity(intentUp);
                    break;
                default:
                    break;

            }
        }
        return false;
    }
}

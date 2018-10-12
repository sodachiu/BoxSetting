package com.example.eileen.boxsetting;

import android.content.Context;
import android.content.Intent;
import android.net.Network;
import android.net.NetworkUtils;
import android.net.ethernet.EthernetManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnKeyListener{

    private static final String TAG = "MainActivity";

    private static final String DEVICE_MODEL = Build.MODEL;
    private static final String SOFTWARE_RELEASE = new Build.VERSION().INCREMENTAL;
    private static final String ANDROID_RELEASE = new Build.VERSION().RELEASE;
    private static final String MAC_ADDRESS = "";
    private static final String STBID = Build.SERIAL;
    private static final String AUTHENTICATE_MAIN_ADDRESS = "";
    private static final String AUTHENTICATE_BACKUP_ADDRESS = "";
    private static final String TERMINAL_MAIN_ADDRESS = "";
    private static final String TERMINAL_BACKUP_ADDRESS = "";
    private static final String AUTHENTICATE_ACCOUNT = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView myInfo = (TextView) findViewById(R.id.my_info);
        TextView deviceModel = (TextView) findViewById(R.id.dev_model);
        TextView softwareRelease = (TextView) findViewById(R.id.software_release);
        TextView androidRelease = (TextView) findViewById(R.id.android_release);
        TextView macAddress = (TextView) findViewById(R.id.mac_addr);
        TextView stbid = (TextView) findViewById(R.id.stbid);
        TextView authMainAddr = (TextView) findViewById(R.id.auth_main_addr);
        TextView authBackupAddr = (TextView) findViewById(R.id.auth_backup_addr);
        TextView terminalMainAddr = (TextView) findViewById(R.id.terminal_main_addr);
        TextView terminalBackupAddr = (TextView) findViewById(R.id.terminal_backup_addr);
        TextView authAccount = (TextView) findViewById(R.id.auth_account);

        deviceModel.setText(DEVICE_MODEL);
        softwareRelease.setText(SOFTWARE_RELEASE);
        androidRelease.setText(ANDROID_RELEASE);
        stbid.setText(STBID);

        Context context = getApplicationContext();

        EthernetManager ethernetManager = (EthernetManager)context.getSystemService(
                Context.ETHERNET_SERVICE);
        int dns1 = ethernetManager.getDhcpInfo().dns1;
        String testDns = NetworkUtils.intToInetAddress(dns1).getHostAddress();


        Log.d(TAG, "onCreate: " + ethernetManager.getIpv6DatabaseDns1() );
        Log.d(TAG, "onCreate: " + testDns);
        Log.d(TAG, "onCreate: " + ethernetManager.getDhcpInfo().toString());

//        Log.d(TAG, "onCreate: " + Build.IS_DEBUGGABLE);

        myInfo.setFocusable(true);
        myInfo.setBackgroundResource(R.drawable.menu_focus_selector);
        myInfo.setOnKeyListener(this);

        //将活动添加到活动列表中
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }



    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN){
            switch (keyCode){
                case KeyEvent.KEYCODE_DPAD_DOWN:
                    Intent intent = new Intent(MainActivity.this, NetSettingActivity.class);
                    startActivity(intent);
                    break;
                default:
                    break;
            }
        }

        return false;
    }

}

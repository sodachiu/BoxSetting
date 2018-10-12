package com.example.eileen.boxsetting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class StoreInfoActivity extends AppCompatActivity
        implements View.OnKeyListener, View.OnClickListener{

    private static final int STORE_INFO = 100;
    private static final String TAG = "StoreInfoActivity";
    private TextView storeInfo;
    private LinearLayout uninstallDev;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_info);
        storeInfo = (TextView) findViewById(R.id.store_info);
        uninstallDev = (LinearLayout) findViewById(R.id.uninstall_out_store_dev);

        storeInfo.setFocusable(true);
        storeInfo.setBackgroundResource(R.drawable.menu_focus_selector);
        storeInfo.setOnKeyListener(this);
        uninstallDev.setOnClickListener(this);

    }

    @Override
    protected void onResume(){
        super.onResume();
        uninstallDev.setFocusable(true);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        ReturnDataUtil.disposeOfData(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View v){
        Intent intent = new Intent(StoreInfoActivity.this, DevDialogActivity.class);
        startActivityForResult(intent, STORE_INFO);
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event){

        if (event.getAction() == KeyEvent.ACTION_DOWN){
            Intent intent;
            switch (keyCode){
                case KeyEvent.KEYCODE_DPAD_DOWN:
                    intent = new Intent(StoreInfoActivity.this, AdvancedActivity.class);
                    startActivity(intent);
                    break;
                case KeyEvent.KEYCODE_DPAD_UP:
                    uninstallDev.setFocusable(false);
                    intent = new Intent(StoreInfoActivity.this, DisplayActivity.class);
                    startActivity(intent);
                    break;
                default:
                    break;
            }
        }
        return false;
    }


}

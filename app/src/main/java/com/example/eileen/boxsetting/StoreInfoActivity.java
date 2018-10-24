package com.example.eileen.boxsetting;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.eileen.boxsetting.StoreInfo.StorageUtils;
import com.example.eileen.boxsetting.StoreInfo.StoreInfoLog;
import com.example.eileen.boxsetting.StoreInfo.UninstallDialog;
import com.example.eileen.boxsetting.Utils.ActivityId;



public class StoreInfoActivity extends AppCompatActivity
        implements View.OnKeyListener, View.OnClickListener{

    private TextView tvMenu;
    private LinearLayout llUninstall;
    private TextView tvTotal;
    private TextView tvTotalDevices1;
    private TextView tvAlt;
    private TextView tvTotalDevices2;

    private MediaReceiver mediaReceiver;
    private Context mContext;

    private static final String MEDIA_EJECT = "android.intent.action.MEDIA_EJECT";
    private static final String MEDIA_MOUNTED = "android.intent.action.MEDIA_MOUNTED";
    private static final String MEDIA_UNMOUNTED = "android.intent.action.MEDIA_UNMOUNTED";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_info_activity);
        tvMenu = (TextView) findViewById(R.id.store_info);
        llUninstall = (LinearLayout) findViewById(R.id.uninstall_out_store_dev);
        tvTotal = (TextView) findViewById(R.id.store_tv_total);
        tvTotalDevices1 = (TextView) findViewById(R.id.store_tv_device_count1);
        tvAlt = (TextView) findViewById(R.id.store_tv_alternative);
        tvTotalDevices2 = (TextView) findViewById(R.id.store_tv_devices_count2);
        mContext = getApplicationContext();


        tvMenu.setFocusable(true);
        tvMenu.setBackgroundResource(R.drawable.menu_focus_selector);
        tvMenu.setOnKeyListener(this);
        llUninstall.setOnClickListener(this);

        IntentFilter filter = new IntentFilter();
        filter.addAction(MEDIA_EJECT); //物理拔出打开状态的sd卡
        filter.addAction(MEDIA_MOUNTED); //机顶盒正确挂载sd卡
        filter.addAction(MEDIA_UNMOUNTED); //sd卡存在，但没有挂载
        filter.addDataScheme("file");

        mediaReceiver = new MediaReceiver();
        registerReceiver(mediaReceiver, filter);


    }

    @Override
    protected void onResume(){
        super.onResume();
        llUninstall.setFocusable(true);
        refreshView();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        unregisterReceiver(mediaReceiver);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        ReturnDataUtil.disposeOfData(requestCode, resultCode, data);
    }

    class MediaReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            refreshView();

        }
    }

    @Override
    public void onClick(View v){
        Intent intent = new Intent(StoreInfoActivity.this, UninstallDialog.class);
        startActivityForResult(intent, ActivityId.STORE_INFO_ACTIVITY);
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
                    llUninstall.setFocusable(false);
                    intent = new Intent(StoreInfoActivity.this, DisplayActivity.class);
                    startActivity(intent);
                    break;
                default:
                    break;
            }
        }
        return false;
    }

    public void refreshView(){
        int devCount = 0;
        long totalSize = 0;
        long availableSize = 0;
        String path1 = "/mnt/sda/sda1";
        String path2 = "/mnt/sdb/sdb1";
        String path3 = "/mnt/sdc/sdc1";
        String path4 = "/mnt/sdd/sdd1";
        boolean path1Exists = StorageUtils.fileIsExists(path1);
        boolean path2Exists = StorageUtils.fileIsExists(path2);
        boolean path3Exists = StorageUtils.fileIsExists(path3);
        boolean path4Exists = StorageUtils.fileIsExists(path4);

        totalSize += StorageUtils.getRomTotalSize();
        availableSize += StorageUtils.getRomAvailableSize();
        if(path1Exists){
            ++devCount;
            totalSize += StorageUtils.getExternalTotalSize(path1);
            availableSize += StorageUtils.getExternalAvailableSize(path1);
        }
        if (path2Exists){
            ++devCount;
            totalSize += StorageUtils.getExternalTotalSize(path2);
            availableSize += StorageUtils.getExternalAvailableSize(path2);
        }
        if (path3Exists){
            ++devCount;
            totalSize += StorageUtils.getExternalTotalSize(path3);
            availableSize += StorageUtils.getExternalAvailableSize(path3);
        }
        if (path4Exists){
            ++devCount;
            totalSize += StorageUtils.getExternalTotalSize(path4);
            availableSize += StorageUtils.getExternalAvailableSize(path4);
        }

        String sTotalSize = Formatter.formatFileSize(mContext, totalSize);
        String sAvailableSize = Formatter.formatFileSize(mContext, availableSize);
        String totalDev = "存储设备个数为： " + devCount;
        tvTotalDevices1.setText(totalDev);
        tvTotalDevices2.setText(totalDev);
        tvTotal.setText(sTotalSize);
        tvAlt.setText(sAvailableSize);
    }

}

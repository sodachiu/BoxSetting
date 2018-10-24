package com.example.eileen.boxsetting;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.os.ServiceManager;
import android.os.storage.IMountService;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eileen.boxsetting.StoreInfo.MyMountService;
import com.example.eileen.boxsetting.StoreInfo.StorageUtils;
import com.example.eileen.boxsetting.StoreInfo.StorageLog;
import com.example.eileen.boxsetting.StoreInfo.UninstallDialog;
import com.example.eileen.boxsetting.Utils.ActivityId;

import static com.android.internal.content.PackageHelper.getMountService;


public class StorageActivity extends AppCompatActivity
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
    private static final String PATH1 = "/mnt/sda/sda1";
    private static final String PATH2 = "/mnt/sda/sdb1";
    private static boolean path1Exists = false;
    private static boolean path2Exists = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.storage_activity);
        tvMenu = (TextView) findViewById(R.id.store_info);
        llUninstall = (LinearLayout) findViewById(R.id.storage_unmount);
        tvTotal = (TextView) findViewById(R.id.storage_tv_total);
        tvTotalDevices1 = (TextView) findViewById(R.id.storage_tv_devices_count1);
        tvAlt = (TextView) findViewById(R.id.storage_tv_available);
        tvTotalDevices2 = (TextView) findViewById(R.id.storage_tv_devices_count2);
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
        if (requestCode != ActivityId.STORE_INFO_ACTIVITY){
            Toast.makeText(mContext, "存储信息位置返回的值不对", Toast.LENGTH_SHORT).show();
        }else {
            StorageLog.LOGI("我进来了吗");
            switch (resultCode){
                case RESULT_OK:
                    IMountService mountService;
                    IBinder service = ServiceManager.getService("mount");

                    if (service != null){
                        mountService = IMountService.Stub.asInterface(service);
                    }else {
                        StorageLog.LOGI("获取服务失败");
                        return;
                    }

                    if (mountService == null){
                        StorageLog.LOGI("没有获取到挂载服务");
                        return;
                    }


                    if (path1Exists){
                        try {
                            mountService.unmountVolume(PATH1, true, false);
                            StorageLog.LOGI("卸载设备1成功");

                        }catch (Exception e){
                            StorageLog.LOGI("卸载设备1失败");
                            StorageLog.LOGI(e.getMessage());
                        }
                    }

                    if (path2Exists){
                        try {
                            mountService.unmountVolume(PATH2, true, false);
                            StorageLog.LOGI("卸载设备2成功");

                        }catch (Exception e){
                            StorageLog.LOGI("卸载设备2失败");
                            StorageLog.LOGI(e.getMessage());
                        }
                    }

                    break;
                case RESULT_CANCELED:
                    StorageLog.LOGI("取消卸载操作");
                    break;
                default:
                    break;
            }
        }
    }

    class MediaReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            refreshView();

        }
    }



    @Override
    public void onClick(View v){
        Intent intent = new Intent(StorageActivity.this, UninstallDialog.class);
        startActivityForResult(intent, ActivityId.STORE_INFO_ACTIVITY);
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event){

        if (event.getAction() == KeyEvent.ACTION_DOWN){
            Intent intent;
            switch (keyCode){
                case KeyEvent.KEYCODE_DPAD_DOWN:
                    intent = new Intent(StorageActivity.this, AdvancedActivity.class);
                    startActivity(intent);
                    break;
                case KeyEvent.KEYCODE_DPAD_UP:
                    llUninstall.setFocusable(false);
                    intent = new Intent(StorageActivity.this, DisplayActivity.class);
                    startActivity(intent);
                    break;
                default:
                    break;
            }
        }
        return false;
    }

    public void refreshView(){
        int devCount = 1;
        long totalSize = 0;
        long availableSize = 0;

        path1Exists = StorageUtils.fileIsExists(PATH1);
        path2Exists = StorageUtils.fileIsExists(PATH2);


        totalSize += StorageUtils.getRomTotalSize();
        availableSize += StorageUtils.getRomAvailableSize();
        if(path1Exists){
            ++devCount;
            totalSize += StorageUtils.getExternalTotalSize(PATH1);
            availableSize += StorageUtils.getExternalAvailableSize(PATH1);
        }
        if (path2Exists){
            ++devCount;
            totalSize += StorageUtils.getExternalTotalSize(PATH2);
            availableSize += StorageUtils.getExternalAvailableSize(PATH2);
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

package com.example.eileen.boxsetting;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.storage.OnObbStateChangeListener;
import android.os.storage.StorageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
    private static final String PATH1 = "/mnt/sda/sda1";
    private static final String PATH2 = "/mnt/sda/sdb1";

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
        if (requestCode != ActivityId.STORE_INFO_ACTIVITY){
            Toast.makeText(mContext, "存储信息位置返回的值不对", Toast.LENGTH_SHORT).show();
        }else {
            StoreInfoLog.LOGI("我进来了吗");
            switch (resultCode){
                case RESULT_OK:
                    StorageManager sm = (StorageManager) mContext.getSystemService(STORAGE_SERVICE);
                    try {
                        boolean path1IsExist = sm.isObbMounted(PATH1);
                        boolean path2IsExist = sm.isObbMounted(PATH2);

                        if (path1IsExist){
                            sm.unmountObb(PATH1, true, new OnObbStateChangeListener() {
                                @Override
                                public void onObbStateChange(String path, int state) {
                                    super.onObbStateChange(path, state);
                                }
                            });
                        }
                        if (path2IsExist){
                            sm.unmountObb(PATH2, true, new OnObbStateChangeListener() {
                                @Override
                                public void onObbStateChange(String path, int state) {
                                    super.onObbStateChange(path, state);
                                }
                            });
                        }
                       /* String keyCommand = "rm -rf /mnt/sda/*";
                        Runtime runtime = Runtime.getRuntime();
                        Process proc = runtime.exec(keyCommand);*/
                        StoreInfoLog.LOGI("卸载成功");

                    }catch (Exception e){
                        StoreInfoLog.LOGI("卸载命令失败");
                        StoreInfoLog.LOGI(e.getMessage());
                    }
                    break;
                case RESULT_CANCELED:
                    StoreInfoLog.LOGI("取消卸载操作");
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
        int devCount = 1;
        long totalSize = 0;
        long availableSize = 0;

        boolean path1Exists = StorageUtils.fileIsExists(PATH1);
        boolean path2Exists = StorageUtils.fileIsExists(PATH2);


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

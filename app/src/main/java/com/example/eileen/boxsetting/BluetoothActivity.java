package com.example.eileen.boxsetting;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eileen.boxsetting.Utils.ActivityId;
import com.example.eileen.boxsetting.bluetooth.PairedDevicesAdapter;
import com.example.eileen.boxsetting.bluetooth.Constent;
import com.example.eileen.boxsetting.bluetooth.UnPairedDevicesAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BluetoothActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView netSetting;
    private Button btnBluetoothStatus;
    private LinearLayout llBluetoothSwitchStatus;
    RecyclerView rvPairedDevices;
    RecyclerView rvUnpairedDevices;


    private BluetoothAdapter mBluetoothAdapter;
    private Set<BluetoothDevice> mSetPairedDevices;
    private List<BluetoothDevice> mPairedDevicesList = new ArrayList<>();
    private List<BluetoothDevice> mUnpairedDevicesList = new ArrayList<>();
    private PairedDevicesAdapter mAdapter1;
    private UnPairedDevicesAdapter mAdapter2;
    private myBluetoothReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bluetooth_activity);
        btnBluetoothStatus = (Button) findViewById(R.id.bluetooth_btn_status);
        rvPairedDevices = (RecyclerView) findViewById(R.id.bluetooth_rv_paired_list);
        rvUnpairedDevices = (RecyclerView) findViewById(R.id.bluetooth_rv_unpaired_list);
        llBluetoothSwitchStatus = (LinearLayout) findViewById(R.id.bluetooth_ll_switch_status);
        netSetting = (TextView) findViewById(R.id.net_setting);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this);


        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null){
            Toast.makeText(BluetoothActivity.this,
                    "此设备不支持蓝牙",
                    Toast.LENGTH_SHORT).show();
            Constent.LOGI("该设备不支持蓝牙");
            return;
        }

        mAdapter1 = new PairedDevicesAdapter(mPairedDevicesList);
        mAdapter2 = new UnPairedDevicesAdapter(mUnpairedDevicesList);

        netSetting.setBackgroundResource(R.drawable.menu_item_select);
        llBluetoothSwitchStatus.setOnClickListener(this);

        rvPairedDevices.setLayoutManager(layoutManager);
        rvUnpairedDevices.setLayoutManager(layoutManager1);
        rvPairedDevices.setAdapter(mAdapter1);
        rvUnpairedDevices.setAdapter(mAdapter2);

        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        filter.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
        filter.addAction(BluetoothDevice.ACTION_ACL_CONNECTED);
        filter.addAction(BluetoothDevice.ACTION_ACL_DISCONNECTED);
        filter.addAction(BluetoothDevice.ACTION_FOUND);
        mReceiver = new myBluetoothReceiver();
        registerReceiver(mReceiver, filter);


    }

    @Override
    protected void onResume(){
        super.onResume();
        if (mBluetoothAdapter.isEnabled()){
            btnBluetoothStatus.setBackgroundResource(R.drawable.checkbox_on);
            if (!mBluetoothAdapter.isDiscovering()) {
                mBluetoothAdapter.startDiscovery();
            }
            refreshView(true);
        }else {

            btnBluetoothStatus.setBackgroundResource(R.drawable.checkbox_off);
            refreshView(false);

        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        if (ActivityId.BLUETOOTH_ACTIVITY != requestCode){
            Constent.LOGI("取消配对的 requestCode 有问题呢");
            return;
        }else if (resultCode == RESULT_CANCELED){
            Constent.LOGI("用户取消了取消配对操作");
            return;
        }
        String deviceInfo = data.getStringExtra("device_info");
        int position = data.getIntExtra("device_position", -1);

        BluetoothDevice remoteDevice = mPairedDevicesList.get(position);
        String remoteInfo;
        if (remoteDevice.getName() != null && !remoteDevice.getName().equals("")){
            remoteInfo = remoteDevice.getName();
        }else {
            remoteInfo = remoteDevice.getAddress();
        }


        if (remoteInfo.equals(deviceInfo)){
            //将已绑定列表中得那项移出，但不加入未绑定列表
            Constent.LOGI(mAdapter1.getItemCount() + "现在的匹配数量");
            mPairedDevicesList.remove(position);
            mAdapter1.notifyItemRemoved(position);
            mAdapter1.notifyItemRangeChanged(position, 1);
            Constent.LOGI(mAdapter1.getItemCount() + "现在的匹配数量");
            remoteDevice.removeBond();
        }else {
            Constent.LOGI("找不到匹配项");

        }
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.bluetooth_ll_switch_status:
                Constent.LOGI(" 你点击我了");
                if (mBluetoothAdapter.isEnabled()) {

                    btnBluetoothStatus.setBackgroundResource(R.drawable.checkbox_off);
                    mBluetoothAdapter.cancelDiscovery();
                    mBluetoothAdapter.disable();

                    rvPairedDevices.setVisibility(View.GONE);
                    rvUnpairedDevices.setVisibility(View.GONE);
                    Constent.LOGI("关闭蓝牙");
                } else {
                    btnBluetoothStatus.setBackgroundResource(R.drawable.checkbox_on);
                    mBluetoothAdapter.enable();

                    rvPairedDevices.setVisibility(View.VISIBLE);
                    rvUnpairedDevices.setVisibility(View.VISIBLE);
                    refreshView(true);
                    Constent.LOGI("开启蓝牙");
                }
        }
    }

    public void refreshView(boolean flag){

        if (!flag){
            rvPairedDevices.setVisibility(View.INVISIBLE);
            rvUnpairedDevices.setVisibility(View.INVISIBLE);
            return;
        }
        if (mBluetoothAdapter.getScanMode() != BluetoothAdapter.SCAN_MODE_CONNECTABLE
                &&
                mBluetoothAdapter.getScanMode() != BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE
                ){

            mBluetoothAdapter.setScanMode(BluetoothAdapter.SCAN_MODE_CONNECTABLE, 86400000);

        }else {
            Constent.LOGD("不知道是什么扫描模式" + mBluetoothAdapter.getScanMode());
        }

        mSetPairedDevices = mBluetoothAdapter.getBondedDevices();
        mPairedDevicesList.clear();
        mPairedDevicesList.addAll(mSetPairedDevices);

        mAdapter1 = new PairedDevicesAdapter(mPairedDevicesList);
        rvPairedDevices.setAdapter(mAdapter1);

        mUnpairedDevicesList.clear();
        mAdapter2 = new UnPairedDevicesAdapter(mUnpairedDevicesList);
        rvUnpairedDevices.setAdapter(mAdapter2);
    }
}

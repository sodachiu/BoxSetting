package com.example.eileen.boxsetting.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.eileen.boxsetting.BluetoothActivity;

import java.util.List;
import java.util.Set;

public class MyBluetoothReceiver extends BroadcastReceiver {

    private List<BluetoothDevice> mList1;
    private List<BluetoothDevice> mList2;
    private BluetoothAdapter mBluetoothAdapter;

    public MyBluetoothReceiver(){
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Constent.LOGI(action);
        if (BluetoothAdapter.ACTION_STATE_CHANGED.equals(action)) {
            /*
            * EXTRA_STATE有四种结果
            * ----STATE_TURNING_ON
            * ----STATE_ON
            * ----STATE_TURNING_OFF
            * ----STATE_OFF
            * */
            int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE,
                    BluetoothAdapter.ERROR);
            switch (state) {
                case BluetoothAdapter.STATE_TURNING_OFF:
                    Constent.LOGI("蓝牙正在关闭，尝试断开断开所有正常连接");
                    Set<BluetoothDevice> tmpList = mBluetoothAdapter.getBondedDevices();
                    for (BluetoothDevice tmpDevice : tmpList){
                        tmpDevice.removeBond();
                    }

                    break;
                case BluetoothAdapter.STATE_OFF:
                    Constent.LOGI("蓝牙处于关闭状态.");
                    break;
                case BluetoothAdapter.STATE_ON:
                    Constent.LOGI("蓝牙开启,开启后才能使用适配器");

                    if (mBluetoothAdapter.isEnabled()) {
                        Constent.LOGI("蓝牙开启，蓝牙适配器可用");
                        //5.2修改
                        if (mBluetoothAdapter.isDiscovering()) {
                            Constent.LOGD("蓝牙开启成功，停止搜索设备");
                            mBluetoothAdapter.cancelDiscovery();
                        }

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                //如果蓝牙正在搜索，则停止搜索，然后再重新开始搜索
                                if (mBluetoothAdapter.isDiscovering()) {
                                    mBluetoothAdapter.cancelDiscovery();
                                }
                                //启动扫描任务，
                                mBluetoothAdapter.startDiscovery();
                            }
                        }).start();
                        Constent.LOGI(" after sleep, start Discovery");

                    } else {
                        Constent.LOGI("蓝牙开启，但蓝牙适配器不可用");
                        mBluetoothAdapter.setScanMode(BluetoothAdapter.SCAN_MODE_CONNECTABLE, 86400000);
                        Constent.LOGI("fail to enable mBluetoothAdapte");
                    }
                    break;
                default:
                    Constent.LOGI("没有找到指定的状态");
                    break;

            }
        } else if (BluetoothDevice.ACTION_FOUND.equals(action)) {
            //处理可连接设备
                /*new Thread(new Runnable() {
                    @Override
                    public void run() {

                    }
                }).start();*/
            BluetoothDevice foundDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            if (foundDevice == null) {
                Constent.LOGI("没有获取到可连接设备");
                return;
            }

            if (foundDevice.getBondState() != BluetoothDevice.BOND_BONDED) {
                for (int i = 0; i < mPairedDevicesList.size(); i++) {

                    BluetoothDevice tempBoundDevice = mPairedDevicesList.get(i);
                    if (tempBoundDevice == null) {
                        continue;
                    }

                    if (foundDevice.getAddress() != null
                            &&
                            foundDevice.getAddress().equals(tempBoundDevice.getAddress())) {
                        //获取到的设备没有名字但有mac的情况
                        Constent.LOGI("获取到两个mac地址相同的设备-> 获取到的设备地址为:"
                                + foundDevice.getAddress()
                                + " 已连接设备的地址为" + tempBoundDevice.getAddress());

                    }
                }

                for (int j = 0; j < mUnpairedDevicesList.size(); j++) {
                    BluetoothDevice tempUnboundDevice = mUnpairedDevicesList.get(j);
                    if (tempUnboundDevice == null){
                        Constent.LOGI("已连接设备有空值");
                        continue;

                    }

                    if (tempUnboundDevice.getAddress().equals(foundDevice.getAddress())) {
                        if (foundDevice.getName() == null || foundDevice.getName().isEmpty()) {
                            Constent.LOGI("get one addr repeat bluetooth ,return .");
                            return;
                        }else if (tempUnboundDevice.getName() == null || tempUnboundDevice.getName().equals("")){
                            mUnpairedDevicesList.remove(j);
                            Constent.LOGI("找到一个同地址名字不为空的，替换前边名字为空的设备");
                        }
                    }

                }



                if (foundDevice.getName() == null || foundDevice.getName().isEmpty()) {

                    mUnpairedDevicesList.add(foundDevice);
                    Constent.LOGI("添加了一个没有名字的设备在未绑定列表中");
                } else {
                    Constent.LOGD("in nameNotNull device" + foundDevice.getAddress());

                    mUnpairedDevicesList.add(foundDevice);

                }
            }

        } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
            // 做一些搜索完成的事情,如果adapter ！= null 刷新界面

            if (mAdapter2 != null) {
                mAdapter2.notifyDataSetChanged();
            } else {
                Constent.LOGI("mAdapter2 is null");
            }
            if (mAdapter1 != null) {
                mAdapter1.notifyDataSetChanged();
            }

        } else if (BluetoothDevice.ACTION_BOND_STATE_CHANGED.equals(action)) {
            //绑定事件发生改变
            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            switch (device.getBondState()) {
                case BluetoothDevice.BOND_BONDING://正在配对
                    if (device.getName() == null || device.getName().isEmpty()) {
                        Constent.LOGI(device.getAddress() + "   BOND_BONDING");
                    } else {
                        Constent.LOGI(device.getName() + "   BOND_BONDING");
                    }

                    break;
                case BluetoothDevice.BOND_BONDED://配对结束

                    if (device.getName() == null || device.getName().isEmpty()) {
                        Constent.LOGI(device.getAddress() + "   BOND_BONDED");
                    } else {
                        Constent.LOGI(device.getName() + "   BOND_BONDED");
                    }

//                        moveUnpairToPairedList(device);
                    //connectDevice(device);
                    Toast.makeText(BluetoothActivity.this,
                            "配对成功！",
                            Toast.LENGTH_SHORT).show();

                    break;
                case BluetoothDevice.BOND_NONE://取消配对/未配对

                    if (device.getName() == null || device.getName().isEmpty()) {
                        Constent.LOGI(device.getAddress() + "   BOND_NONE");
                    } else {
                        Constent.LOGI(device.getName() + "   BOND_NONE");
                    }
//                        movePairToUnPairList(device);

                default:
                    break;
            }

                /*if (mAdapter1 != null) {
                    mAdapter1.updateBondState();
                    mAdapter1.notifyDataSetChanged();
                }*/
        }
    }
}

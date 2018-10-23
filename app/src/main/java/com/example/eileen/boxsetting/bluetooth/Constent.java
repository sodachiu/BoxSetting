package com.example.eileen.boxsetting.bluetooth;

import android.util.Log;

public class Constent {

    public static boolean bDebugInfo = true;
    public static String TAG = "mybluetooth";

    public static String ListDeviceName="device_name";
    public static String ListDeviceInfo="device_info";
    public static String ListDeviceAddress="device_addr";
    public static String ListDevicePairState="device_pairstate";


    public static void LOGI(String msLog)
    {

        Log.i(Constent.TAG, msLog);

    }

    public static void LOGD(String msLog)
    {
        if (Constent.bDebugInfo) {
            Log.d(Constent.TAG, msLog);
        }
    }

    public static void LOGE(String msLog)
    {

        Log.e(Constent.TAG, msLog);

    }
}

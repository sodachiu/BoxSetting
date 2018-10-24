package com.example.eileen.boxsetting.StoreInfo;

import android.util.Log;

public class StorageLog {

    public static boolean bDebugInfo = true;
    public static String TAG = "mystoreinfo";




    public static void LOGI(String msLog)
    {

        Log.i(StorageLog.TAG, msLog);

    }

    public static void LOGD(String msLog)
    {
        if (StorageLog.bDebugInfo) {
            Log.d(StorageLog.TAG, msLog);
        }
    }

    public static void LOGE(String msLog)
    {

        Log.e(StorageLog.TAG, msLog);

    }
}

package com.example.eileen.boxsetting.StoreInfo;

import android.util.Log;

public class StoreInfoLog {

    public static boolean bDebugInfo = true;
    public static String TAG = "mystoreinfo";




    public static void LOGI(String msLog)
    {

        Log.i(StoreInfoLog.TAG, msLog);

    }

    public static void LOGD(String msLog)
    {
        if (StoreInfoLog.bDebugInfo) {
            Log.d(StoreInfoLog.TAG, msLog);
        }
    }

    public static void LOGE(String msLog)
    {

        Log.e(StoreInfoLog.TAG, msLog);

    }
}

package com.example.eileen.boxsetting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class ReturnDataUtil {
    private static final int STORE_INFO = 100;
    private static final String TAG = "ReturnDataUtil";
    private static AppCompatActivity activity = ActiveActivity.getActiveActivity();
    private static int ok = activity.RESULT_OK;
    private static int cancel = activity.RESULT_CANCELED;
    public static void disposeOfData(int requestCode, int resultCode, Intent data){
        switch (requestCode){
            case STORE_INFO:
                if (resultCode == ok){
                    //做确定处理
                    Log.d(TAG, "onActivityResult: " + ok);
                }else{
                    Log.d(TAG, "onActivityResult: " + cancel);
                    Log.d(TAG, "onActivityResult: 返回码错误" );
                }
                break;

            default:
                break;
        }
    }
}

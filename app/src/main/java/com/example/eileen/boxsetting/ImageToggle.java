package com.example.eileen.boxsetting;

import android.provider.Settings;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ImageToggle {

    private static String TAG = "ImageToggle";
    private static int count = 0;

    public static void toggle(String status, LinearLayout layout,TextView textView){

        //设置日期和时间部分的话，完全可以根据textClock的format来动态加载开关图片。
        // 暂时未完成
        if (status.equals("off")){
            textView.setBackgroundResource(R.drawable.checkbox_on);
            layout.setContentDescription("on");
            Log.d(TAG, "toggle: " + Settings.System.getString(
                    ActiveActivity.getActiveActivity().getContentResolver()
                    ,Settings.System.DATE_FORMAT));
        }else if (status.equals("on")){
            textView.setBackgroundResource(R.drawable.checkbox_off);
            layout.setContentDescription("off");
        }else{
            Log.d(TAG, "toggle: contentDesciption is wrong " + status);
        }

    }
}

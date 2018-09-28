package com.example.eileen.boxsetting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;


public class MenuUtil {
    private static TextView tv;
    private static String TAG = "MenuUtil";
    private static AppCompatActivity activeActivity = ActiveActivity.getActiveActivity();


/*
*
* 根据textView的id值确定按键监听事件
*----textView：menu中具有焦点的textView
* */
    public static void onKeyListener(TextView textView) {
        tv = textView;

        if (textView.getId() == R.id.my_info ){

            tv.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    myInfoEvent(keyCode, event);
                    return false;
                }
            });
        }else if (textView.getId() == R.id.net_setting){
            tv.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    netSettingEvent(keyCode, event);
                    return false;
                }
            });
        }else if (textView.getId() == R.id.net_info){

        }else if(textView.getId() == R.id.date_time){

        }else if (textView.getId() == R.id.display){

        }else if (textView.getId() == R.id.store_info){

        }else if (textView.getId() == R.id.advanced){

        }else if (textView.getId() == R.id.res_factory){

        }else {
            Log.d(TAG, "onKeyListener: fault");
        }
    }

/*
*
* 焦点在“关于本机”时，按键监听事件需要完成的动作
* ----keyCode：操作的键值
* ----event：包括按下和回弹
* */
    private static void myInfoEvent(int keyCode, KeyEvent event) {

        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_DPAD_DOWN:
                    Intent intent = new Intent(activeActivity, NetSettingActivity.class);
                    activeActivity.startActivity(intent);
                    break;
                default:
                    break;
            }
        }
    }


/*
*
* 焦点在“网络设置”时，按键监听事件需要完成的动作
* */
    private static void netSettingEvent(int keyCode, KeyEvent event){

    }


}
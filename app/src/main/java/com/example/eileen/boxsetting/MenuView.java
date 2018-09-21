package com.example.eileen.boxsetting;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class MenuView extends Activity implements View.OnFocusChangeListener{
    private AppCompatActivity appCA;
    private static final String TAG = "MenuView";
    private ViewGroup viewGroup;

    public MenuView(ViewGroup parent, AppCompatActivity appCompatActivity){

         viewGroup = parent;
         appCA = appCompatActivity;

        TextView myInfo = (TextView) viewGroup.findViewById(R.id.my_info);
        TextView netSetting = (TextView) viewGroup.findViewById(R.id.net_setting);
        TextView netInfo = (TextView) viewGroup.findViewById(R.id.net_info);
        TextView dateTime = (TextView) viewGroup.findViewById(R.id.date_time);
        TextView display = (TextView) viewGroup.findViewById(R.id.display);
        TextView storeInfo = (TextView) viewGroup.findViewById(R.id.store_info);
        TextView advanced = (TextView) viewGroup.findViewById(R.id.advanced);
        TextView resFactory = (TextView) viewGroup.findViewById(R.id.res_factory);


        myInfo.setOnFocusChangeListener(this);
        netSetting.setOnFocusChangeListener(this);
        netInfo.setOnFocusChangeListener(this);
        dateTime.setOnFocusChangeListener(this);
        display.setOnFocusChangeListener(this);
        storeInfo.setOnFocusChangeListener(this);
        advanced.setOnFocusChangeListener(this);
        resFactory.setOnFocusChangeListener(this);

    }

    @Override
    public void onFocusChange(View v, boolean hasFocus){

        FragmentView fragmentView = new FragmentView(appCA);
        v.setBackgroundResource(R.drawable.menu_focus_selector);

        if (hasFocus){
            switch (v.getId()){
                case R.id.my_info:
                    fragmentView.repFragment(new FragmentMyInfo(), "my_info");
                    break;
                case R.id.net_setting:
                    fragmentView.repFragment(new FragmentNetSetting(), "net_setting");
                    break;
                case R.id.net_info:
                    fragmentView.repFragment(new FragmentNetInfo(), "net_info");
                    break;
                case R.id.date_time:
                    fragmentView.repFragment(new FragmentDateTime(), "date_time");
                    break;
                case R.id.display:
                    fragmentView.repFragment(new FragmentDisplay(),"display");
                    break;
                case R.id.store_info:
                    fragmentView.repFragment(new FragmentStoreInfo(), "store_info");
                    break;
                case R.id.advanced:
                    fragmentView.repFragment(new FragmentAdvanced(), "advanced");
                    break;
                case R.id.res_factory:
                    fragmentView.repFragment(new FragmentResFactory(), "res_factory");
                    break;
                default:
                    break;
            }
        }else{



            boolean flag = menuItemIsFocused((TextView) v);
            Log.d(TAG, "onFocusChange: " + flag);

            if (flag){

                // 如果焦点还在menu布局内，清空回退栈，以便其它菜单项加入回退栈
            }else{

                //保留焦点位置focusLocation，背景改为浅色
                TextView focusLocation = (TextView) v;
                focusLocation.setBackgroundResource(R.drawable.menu_item_select);
            }
        }

    }


    /*
    *
    * 判断menu布局内是否还有焦点
    * */

    private boolean menuItemIsFocused(TextView nowItem) {
        TextView nextItem;
        TextView preItem;

        switch (nowItem.getId()) {
            case R.id.my_info:
                nextItem = (TextView) viewGroup.findViewById(R.id.net_setting);
                preItem = (TextView) viewGroup.findViewById(R.id.res_factory);
                break;

            case R.id.net_setting:
                nextItem = (TextView) viewGroup.findViewById(R.id.net_info);
                preItem = (TextView) viewGroup.findViewById(R.id.my_info);
                break;
            case R.id.net_info:
                nextItem = (TextView) viewGroup.findViewById(R.id.date_time);
                preItem = (TextView) viewGroup.findViewById(R.id.net_setting);
                break;
            case R.id.date_time:
                nextItem = (TextView) viewGroup.findViewById(R.id.display);
                preItem = (TextView) viewGroup.findViewById(R.id.net_info);
                break;
            case R.id.display:
                nextItem = (TextView) viewGroup.findViewById(R.id.store_info);
                preItem = (TextView) viewGroup.findViewById(R.id.date_time);
                break;
            case R.id.store_info:
                nextItem = (TextView) viewGroup.findViewById(R.id.advanced);
                preItem = (TextView) viewGroup.findViewById(R.id.display);
                break;

            case R.id.advanced:
                nextItem = (TextView) viewGroup.findViewById(R.id.res_factory);
                preItem = (TextView) viewGroup.findViewById(R.id.store_info);
                break;

            case R.id.res_factory:
                nextItem = (TextView) viewGroup.findViewById(R.id.my_info);
                preItem = (TextView) viewGroup.findViewById(R.id.advanced);
                break;
            default:
                nextItem = null;
                preItem = null;
                break;


        }

        return (nextItem.isFocused() || preItem.isFocused());

    }


}

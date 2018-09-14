package com.example.eileen.boxsetting;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class MenuView extends Activity implements View.OnFocusChangeListener{
    private AppCompatActivity appAc;
    private static final String TAG = "MenuView";
    private ViewGroup viewGroup;

    public MenuView(ViewGroup parent, AppCompatActivity appCompatActivity){

         appAc = appCompatActivity;
         viewGroup = parent;

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

        FragmentView fragmentView = new FragmentView(appAc);
        v.setBackgroundResource(R.drawable.menu_focus_selector);

        if (hasFocus){
            switch (v.getId()){
                case R.id.my_info:
                    fragmentView.addFragment(new MyInfoFragment(), "my_info");
                    break;
                case R.id.net_setting:
                    fragmentView.addFragment(new NetSettingFragment(), "net_setting");
                    break;
                case R.id.net_info:
                    fragmentView.addFragment(new NetInfoFragment(), "net_info");
                    break;
                case R.id.date_time:
                    fragmentView.addFragment(new DateTimeFragment(), "date_time");
                    break;
                case R.id.display:
                    fragmentView.addFragment(new DisplayFragmnet(),"display");
                    break;
                case R.id.store_info:
                    fragmentView.addFragment(new StoreInfoFragment(), "store_info");
                    break;
                case R.id.advanced:
                    fragmentView.addFragment(new AdvancedFragment(), "advanced");
                    break;
                case R.id.res_factory:
                    fragmentView.addFragment(new ResFactoryFragment(), "res_factory");
                    break;
                default:
                    break;
            }
        }else{



            boolean flag = menuItemIsFocused((TextView) v);
            Log.d(TAG, "onFocusChange: " + flag);

            if (flag){

                // 如果焦点还在menu布局内，清空回退栈，以便其它菜单项加入回退栈
               fragmentView.removeFragment();
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

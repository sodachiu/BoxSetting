package com.example.eileen.boxsetting;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class FragmentBluetooth extends Fragment implements View.OnClickListener{

    private static final String TAG = "FragmentBluetooth";
    private static int flag = 0;
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_bluetooth, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        LinearLayout switchBluetooth = (LinearLayout) getActivity().findViewById(R.id.switch_bluetooh);
        switchBluetooth.requestFocus();
        switchBluetooth.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.switch_bluetooh:
//                layout文件中为ImageView设置了初始图片
//                需要根据接口查看蓝牙状态，再动态设置开关图片
                toggleImage();
                break;
            default:
                break;

        }
    }

    private void toggleImage(){
        ImageView item = (ImageView) getActivity().findViewById(R.id.image_bluetooth_state);
        if (flag%2 == 0){
            item.setImageResource(R.drawable.checkbox_on);

        }else{
            item.setImageResource(R.drawable.checkbox_off);

        }
        ++flag;
    }
}

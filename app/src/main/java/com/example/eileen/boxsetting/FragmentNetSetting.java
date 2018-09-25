package com.example.eileen.boxsetting;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import static android.content.ContentValues.TAG;

public class FragmentNetSetting extends Fragment implements View.OnClickListener, View.OnKeyListener{
    @Override
    public void onAttach(Context context){
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_net_setting, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        LinearLayout setNet = (LinearLayout) getActivity().findViewById(R.id.set_net);
        LinearLayout setBluttooth = (LinearLayout) getActivity().findViewById(R.id.set_bluetooth);
        setNet.setOnClickListener(this);
        setBluttooth.setOnClickListener(this);

        ViewGroup netSetting = (ViewGroup) getActivity().findViewById(R.id.net_setting_layout);
        netSetting.setOnKeyListener(this);


    }

    @Override
    public void onClick(View v) {
        FragmentView fv = new FragmentView((AppCompatActivity) getActivity());
        switch (v.getId()){
            case R.id.set_net:
                fv.addFragment(new FragmentNetSettingWire(), "fg_net_setting_wire");
                break;
            case R.id.set_bluetooth:
                fv.addFragment(new FragmentBluetooth(), "fragment_bluetooth");
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        Log.d(TAG, "onKey: hello");

        Log.d(TAG, "onKey: view" + v);
        Log.d(TAG, "onKey: keyCode:" + keyCode);
        Log.d(TAG, "onKey: event:" + event);
        return false;
    }

}

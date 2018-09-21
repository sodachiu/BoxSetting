package com.example.eileen.boxsetting;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import static android.content.ContentValues.TAG;

public class FragmentNetSettingWire extends Fragment implements View.OnClickListener{
    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        Log.d(TAG, "onAttach: " + context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_net_setting_wire, container,false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        LinearLayout pppoe = (LinearLayout) getActivity().findViewById(R.id.pppoe_container);
        LinearLayout dhcp = (LinearLayout) getActivity().findViewById(R.id.dhcp_container);
        LinearLayout staticIP = (LinearLayout) getActivity().findViewById(R.id.static_ip_container);
        pppoe.requestFocus();
        pppoe.setOnClickListener(this);
        dhcp.setOnClickListener(this);
        staticIP.setOnClickListener(this);

        /*在*/

    }

    @Override
    public void onClick(View v) {
        FragmentView fv = new FragmentView((AppCompatActivity) getActivity());
        switch (v.getId()){
            case R.id.pppoe_container:
                fv.addFragment(new FragmentPPPoE(), "fragment_pppoe");
                break;
            case R.id.dhcp_container:
                /*调用连接动态IP的方法*/
                break;
            case R.id.static_ip_container:
                fv.addFragment(new FragmentStaticIP(), "fragment_static_ip");
            default:
                break;

        }
    }
}

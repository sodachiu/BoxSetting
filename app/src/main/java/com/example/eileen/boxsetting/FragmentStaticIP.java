package com.example.eileen.boxsetting;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class FragmentStaticIP extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_static_ip, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        Button confirm = (Button) getActivity().findViewById(R.id.static_ip_confirm);
        confirm.requestFocus();
        confirm.setOnClickListener(this);


    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.static_ip_confirm:
                break;
            case R.id.static_ip_cancel:
                break;
            default:
                break;
        }
    }

}

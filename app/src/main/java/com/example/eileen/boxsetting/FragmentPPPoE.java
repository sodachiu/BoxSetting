package com.example.eileen.boxsetting;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class FragmentPPPoE extends Fragment implements View.OnKeyListener{

    private static final String TAG = "FragmentPPPoE";

    @Override
    public View onCreateView(LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_pppoe, container, false);
        return view;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        EditText username = (EditText) getActivity().findViewById(R.id.pppoe_username);
        EditText userPwd = (EditText) getActivity().findViewById(R.id.pppoe_password);
        username.setOnKeyListener(this);
        username.requestFocus();




    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event){
        if (event.getAction() == KeyEvent.ACTION_DOWN){

            FragmentManager fg = getFragmentManager();
            FragmentTransaction transaction = fg.beginTransaction();
            Log.d(TAG, "onKey: hello" + fg.getBackStackEntryCount());
            fg.popBackStackImmediate();
            transaction.commit();
        }

        return  false;
    }


}

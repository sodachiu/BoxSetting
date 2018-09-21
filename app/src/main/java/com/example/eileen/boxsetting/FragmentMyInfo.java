package com.example.eileen.boxsetting;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class FragmentMyInfo extends Fragment {
    private static String ARG_PARAM = "param_key";
    private static final String TAG = "FragmentMyInfo";
    private AppCompatActivity mActivity;
    private String mParam;

   /* @Override
    public void onAttach(Context context){
        Log.d(TAG, "onAttach: " + context);
        super.onAttach(context);
        mActivity = (AppCompatActivity) context;
        mParam = getArguments().getString(ARG_PARAM);

    }*/

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_my_info, container,false);
        return view;
    }


    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //
        /*TextView devType = (TextView) getActivity().findViewById(R.id.dev_type);
        Log.d(TAG, "onCreateView: " + devType);
        devType.setText(R.string.dev_type);*/

    }

}
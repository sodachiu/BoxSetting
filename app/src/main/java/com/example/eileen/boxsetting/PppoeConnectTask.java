package com.example.eileen.boxsetting;

import android.app.Activity;
import android.content.Context;
import android.net.ethernet.EthernetManager;
import android.net.pppoe.PppoeManager;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;

public class PppoeConnectTask extends AsyncTask<Context, Integer, Integer> {
    private static final String TAG = "PppoeConnectTask";
    public static final int CONNECT_SUCCESS = 1;
    public static final int CONNECT_FAILED = 0;

    private PppoeManager mPppoeManager;
    private EthernetManager mEthManager;

    private PppoeConnectListener pppoeConnectListener;
    private Boolean isCancelConnect = false;

    public PppoeConnectTask(PppoeConnectListener pppoeConnectListener){
        this.pppoeConnectListener = pppoeConnectListener;
    }

    @Override
    protected Integer doInBackground(Context... params){
        Context context = params[0];
        EditText etUsername = ((Activity) context).findViewById(R.id.pppoe_et_username);
        EditText etPassword = ((Activity) context).findViewById(R.id.pppoe_et_password);

        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        String ifaceName;
        if (!username.equals("") && !password.equals("")){
            mPppoeManager = (PppoeManager) context.getSystemService(
                    Context.PPPOE_SERVICE);
            mEthManager = (EthernetManager) context.getSystemService(
                    Context.ETHERNET_SERVICE);
            ifaceName = mEthManager.getInterfaceName();


            try {
                mPppoeManager.connect(username, password, ifaceName);=TER[[]]
            }catch (Exception e){
                e.printStackTrace();
                return CONNECT_FAILED;
            }
        }
        Log.d(TAG, "doInBackground: Context " + params[0]);




        try{

        }

        return CONNECT_FAILED;

    }

    @Override
    protected void onProgressUpdate(Integer... values){

    }

    @Override
    protected void onPostExecute(Integer status){

    }

    public void cancelConnect(){
        isCancelConnect = true;
    }


}

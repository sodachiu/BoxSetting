package com.example.eileen.boxsetting;

import android.os.AsyncTask;

public class PppoeConnectTask extends AsyncTask<String, Integer, Integer> {

    public static final int CONNECT_SUCCESS = 1;
    public static final int CONNECT_FAILED = 0;

    private PppoeConnectListener pppoeConnectListener;
    private Boolean isCancelConnect = false;

    public PppoeConnectTask(PppoeConnectListener pppoeConnectListener){
        this.pppoeConnectListener = pppoeConnectListener;
    }

    @Override
    protected Integer doInBackground(String... params){

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

package com.example.eileen.boxsetting;

import android.app.IntentService;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.DhcpInfo;
import android.net.ethernet.EthernetManager;
import android.net.pppoe.PppoeManager;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class PppoeConnectService extends Service {
    private static final String TAG = "PppoeConnectService";
    private PppoeConnectTask pppoeConnectTask;

    private PppoeConnectListener listener = new PppoeConnectListener() {
        @Override
        public void onProgress() {
            Log.d(TAG, "onProgress: ");
        }

        @Override
        public void onSuccess() {
            Log.d(TAG, "onSuccess: ");
        }

        @Override
        public void onFail() {
            Log.d(TAG, "onFail: ");
        }

        @Override
        public void onCancel() {
            Log.d(TAG, "onCancel: ");
        }
    };

    private PppoeConnectBinder mBinder = new PppoeConnectBinder();
    @Override
    public IBinder onBind(Intent intent){
        return mBinder;
    }

    class PppoeConnectBinder extends Binder{
        public void connect(String username, String pswd){
            if (pppoeConnectTask == null){
                pppoeConnectTask = new PppoeConnectTask(listener);
                pppoeConnectTask.execute(username, pswd);
                Toast.makeText(PppoeConnectService.this,
                        "正在连接",
                        Toast.LENGTH_SHORT).show();
            }
        }

        public void disconnect(){
            if (pppoeConnectTask != null){
                pppoeConnectTask.cancelConnect();
            }
        }

    }
}

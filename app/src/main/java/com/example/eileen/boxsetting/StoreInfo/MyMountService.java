package com.example.eileen.boxsetting.StoreInfo;

import android.os.IBinder;
import android.os.ServiceManager;
import android.os.storage.IMountService;

public class MyMountService {

    public static synchronized IMountService getMountService(){
        IMountService mountService = null;
        IBinder service = ServiceManager.getService("mount");
        if (service != null){
            mountService = IMountService.Stub.asInterface(service);
        }else {
            StorageLog.LOGI("获取服务失败");
        }

        return mountService;
    }
}

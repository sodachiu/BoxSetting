package com.example.eileen.boxsetting;

public interface PppoeConnectListener {
    void onProgress();

    void onSuccess();

    void onFail();

    void onCancel();
}

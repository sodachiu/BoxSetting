package com.example.eileen.boxsetting;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DevDialogActivity extends Activity
        implements View.OnClickListener{
    private Button confirm;
    private Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dev_dialog);

        confirm = (Button) findViewById(R.id.button_uninstall_confirm);
        cancel = (Button) findViewById(R.id.button_uninstall_cancel);

        confirm.setOnClickListener(this);
        cancel.setOnClickListener(this);

    }

    @Override
    public void onClick(View v){
        boolean flag = false;
        switch (v.getId()){
            case R.id.button_uninstall_confirm:


        }
    }
}

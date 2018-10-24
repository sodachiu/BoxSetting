package com.example.eileen.boxsetting.StoreInfo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.eileen.boxsetting.R;

public class UninstallDialog extends Activity
        implements View.OnClickListener{
    private Button confirm;
    private Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.uninstall_dialog);

        confirm = (Button) findViewById(R.id.button_uninstall_confirm);
        cancel = (Button) findViewById(R.id.button_uninstall_cancel);

        confirm.setOnClickListener(this);
        cancel.setOnClickListener(this);

    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.button_uninstall_confirm:
                setResult(RESULT_OK);
                finish();
                break;
            case R.id.button_uninstall_cancel:
                setResult(RESULT_CANCELED);
                finish();
                break;
            default:
                break;

        }
    }
}

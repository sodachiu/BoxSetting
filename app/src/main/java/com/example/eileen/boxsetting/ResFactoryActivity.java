package com.example.eileen.boxsetting;

import android.content.Intent;
import android.os.RecoverySystem;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ResFactoryActivity extends AppCompatActivity
        implements View.OnClickListener, View.OnKeyListener{

    private static final int RES_FACTORY = 99;
    private TextView resFactory;
    private EditText password;
    private Button confirm;
    private LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res_factory);
        resFactory = (TextView) findViewById(R.id.res_factory);
        password = (EditText) findViewById(R.id.res_factory_pwd);
        confirm = (Button) findViewById(R.id.button_res_factory);
        layout = (LinearLayout) findViewById(R.id.layout_res_factory);

        resFactory.setFocusable(true);
        resFactory.setBackgroundResource(R.drawable.menu_focus_selector);
        resFactory.setOnKeyListener(this);
        confirm.setOnKeyListener(this);
        password.setOnKeyListener(this);
        confirm.setOnClickListener(this);

    }

    @Override
    protected void onResume(){
        super.onResume();
        password.setFocusable(true);
        confirm.setFocusable(true);
    }


    /*@Override
    protected void onDestroy(){
        super.onDestroy();
        ActivityCollector.finishAll();
    }*/

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        ReturnDataUtil.disposeOfData(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View v){
        Intent intent = new Intent(ResFactoryActivity.this, ResFactoryDialogActivity.class);
        startActivityForResult(intent, RES_FACTORY);
        /*try{
            RecoverySystem.rebootWipeUserData(getApplicationContext());

        }catch (){

        }*/

    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent keyEvent){


        /*这段看起来比较恼火，是为了能够在按下返回键直接退出程序，之后会应用到所有第一层
        * 活动中，会考虑写个工具类啥的，直接一步操作完。看起来也没那么烦。
        * */
        if (keyEvent.getAction() == KeyEvent.ACTION_DOWN){
            switch (v.getId()){
                case R.id.res_factory:
                    if (keyCode == KeyEvent.KEYCODE_DPAD_UP){
                        password.setFocusable(false);
                        confirm.setFocusable(false);
                        Intent intent = new Intent(ResFactoryActivity.this, AdvancedActivity.class);
                        startActivity(intent);
                    }else if (keyCode == KeyEvent.KEYCODE_BACK){
                        ActivityCollector.finishAll();
                    }else{

                    }
                    break;
                default:
                    ActivityCollector.finishAll();
                    break;

            }

            /*switch (keyCode){
                case KeyEvent.KEYCODE_DPAD_UP:
                    password.setFocusable(false);
                    confirm.setFocusable(false);
                    Intent intent = new Intent(ResFactoryActivity.this, AdvancedActivity.class);
                    startActivity(intent);
                    break;
                default:
                    break;
            }*/
        }
        return false;
    }




}

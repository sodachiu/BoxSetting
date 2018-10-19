package com.example.eileen.boxsetting;

import android.content.Context;
import android.content.Intent;
import android.os.display.DisplayManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ResolutionActivity extends AppCompatActivity {
    private TextView tvMenuItem;
    private List<Resolution> mResolutionList = new ArrayList<>();
    private ResolutionAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private int mPosition;
    private int mCurrentStandardId;
    private static final String TAG = "ResolutionActivity";
    private static final int DISPLAY_ADAPTIVE = 999;
    public static final int RESOLUTION_ACTIVITY = 98;

    private DisplayManager displayManager;
    private int currentStandardId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resolution_activity);
        tvMenuItem = (TextView) findViewById(R.id.display);
        tvMenuItem.setBackgroundResource(R.drawable.menu_item_select);



    }

    protected void onResume(){
        super.onResume();
        Context context = getApplicationContext();
        displayManager = (DisplayManager)context.getSystemService(
                Context.DISPLAY_MANAGER_SERVICE);
        mCurrentStandardId = displayManager.getCurrentStandard();
        initResolutions();
        mRecyclerView = (RecyclerView) findViewById(R.id.resolution_recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new ResolutionAdapter(mResolutionList);
        mRecyclerView.setAdapter(mAdapter);
        Log.d(TAG, "onResume: onresume");

    }

    public void initResolutions(){
        int[] allSupportStandards = displayManager.getAllSupportStandards();
        Resolution displayAdaptive = new Resolution("自适应",
                DISPLAY_ADAPTIVE);
        mResolutionList.add(displayAdaptive);

        for (int item : allSupportStandards) {
            switch (item){
                case DisplayManager.DISPLAY_STANDARD_1080P_60:

                    Resolution display1080P60Hz = new Resolution("1080P 60Hz",
                            DisplayManager.DISPLAY_STANDARD_1080P_60);
                    if (DisplayManager.DISPLAY_STANDARD_1080P_60 == mCurrentStandardId){
                        display1080P60Hz.setIschecked(true);
                    }
                    mResolutionList.add(display1080P60Hz);
                    break;
                case DisplayManager.DISPLAY_STANDARD_1080P_50:
                    Resolution display1080P50Hz = new Resolution("1080P 50Hz",
                            DisplayManager.DISPLAY_STANDARD_1080P_50);
                    if (DisplayManager.DISPLAY_STANDARD_1080P_50 == mCurrentStandardId){
                        display1080P50Hz.setIschecked(true);
                    }
                    mResolutionList.add(display1080P50Hz);
                    break;
                case DisplayManager.DISPLAY_STANDARD_1080P_30:
                    Resolution display1080P30Hz = new Resolution("1080P 30Hz",
                            DisplayManager.DISPLAY_STANDARD_1080P_30);
                    if (DisplayManager.DISPLAY_STANDARD_1080P_30 == mCurrentStandardId){
                        display1080P30Hz.setIschecked(true);
                    }
                    mResolutionList.add(display1080P30Hz);
                    break;
                case DisplayManager.DISPLAY_STANDARD_1080P_25:
                    Resolution display1080P25Hz = new Resolution("1080P 25Hz",
                            DisplayManager.DISPLAY_STANDARD_1080P_25);
                    if (DisplayManager.DISPLAY_STANDARD_1080P_25 == mCurrentStandardId){
                        display1080P25Hz.setIschecked(true);
                    }
                    mResolutionList.add(display1080P25Hz);
                    break;
                case DisplayManager.DISPLAY_STANDARD_1080P_24:
                    Resolution display1080P24Hz = new Resolution("1080P 24Hz",
                            DisplayManager.DISPLAY_STANDARD_1080P_24);
                    if (DisplayManager.DISPLAY_STANDARD_1080P_24 == mCurrentStandardId)
                    mResolutionList.add(display1080P24Hz);
                    break;
                case DisplayManager.DISPLAY_STANDARD_1080I_60:
                    Resolution display1080i60Hz = new Resolution("1080i 60Hz",
                            DisplayManager.DISPLAY_STANDARD_1080I_60);
                    if (DisplayManager.DISPLAY_STANDARD_1080I_60 == mCurrentStandardId){
                        display1080i60Hz.setIschecked(true);
                    }
                    mResolutionList.add(display1080i60Hz);
                    break;
                case DisplayManager.DISPLAY_STANDARD_1080I_50:
                    Resolution display1080i50Hz = new Resolution("1080i 50Hz",
                            DisplayManager.DISPLAY_STANDARD_1080I_50);
                    if (DisplayManager.DISPLAY_STANDARD_1080I_50 == mCurrentStandardId){
                        display1080i50Hz.setIschecked(true);
                    }
                    mResolutionList.add(display1080i50Hz);
                    break;
                case DisplayManager.DISPLAY_STANDARD_720P_60:
                    Resolution display720p60Hz = new Resolution("720P 60Hz",
                            DisplayManager.DISPLAY_STANDARD_720P_60);
                    if (DisplayManager.DISPLAY_STANDARD_720P_60 == mCurrentStandardId){
                        display720p60Hz.setIschecked(true);
                    }
                    mResolutionList.add(display720p60Hz);
                    break;
                case DisplayManager.DISPLAY_STANDARD_720P_50:
                    Resolution display720p50Hz = new Resolution("720P 50Hz",
                            DisplayManager.DISPLAY_STANDARD_720P_50);
                    if (DisplayManager.DISPLAY_STANDARD_720P_50 == mCurrentStandardId){
                        display720p50Hz.setIschecked(true);
                    }
                    mResolutionList.add(display720p50Hz);
                    break;
                case DisplayManager.DISPLAY_STANDARD_480P_60:
                    Resolution display480p60Hz = new Resolution("480P 60Hz",
                            DisplayManager.DISPLAY_STANDARD_480P_60);
                    if (DisplayManager.DISPLAY_STANDARD_480P_60 == mCurrentStandardId){
                        display480p60Hz.setIschecked(true);
                    }
                    mResolutionList.add(display480p60Hz);
                    break;
                case DisplayManager.DISPLAY_STANDARD_PAL:
                    Resolution displayPal = new Resolution("PAL",
                            DisplayManager.DISPLAY_STANDARD_PAL);
                    if (DisplayManager.DISPLAY_STANDARD_PAL == mCurrentStandardId){
                        displayPal.setIschecked(true);
                    }
                    mResolutionList.add(displayPal);
                    break;
                case DisplayManager.DISPLAY_STANDARD_NTSC:
                    Resolution displayNtsc = new Resolution("NTSL",
                            DisplayManager.DISPLAY_STANDARD_NTSC);
                    if (DisplayManager.DISPLAY_STANDARD_NTSC == mCurrentStandardId){
                        displayNtsc.setIschecked(true);
                    }
                    mResolutionList.add(displayNtsc);
                    break;
                case DisplayManager.DISPLAY_STANDARD_3840_2160P_24:
                    Resolution display3840x2160p24Hz = new Resolution("3840 x 2160P 24Hz",
                            DisplayManager.DISPLAY_STANDARD_3840_2160P_24);
                    if (DisplayManager.DISPLAY_STANDARD_3840_2160P_24 == mCurrentStandardId){
                        display3840x2160p24Hz.setIschecked(true);
                    }
                    mResolutionList.add(display3840x2160p24Hz);
                    break;
                case DisplayManager.DISPLAY_STANDARD_3840_2160P_25:
                    Resolution display3840x2160p25Hz = new Resolution("3840 x 2160P 25Hz",
                            DisplayManager.DISPLAY_STANDARD_3840_2160P_25);
                    if (DisplayManager.DISPLAY_STANDARD_3840_2160P_25 == mCurrentStandardId){
                        display3840x2160p25Hz.setIschecked(true);
                    }
                    mResolutionList.add(display3840x2160p25Hz);
                    break;
                case DisplayManager.DISPLAY_STANDARD_3840_2160P_30:
                    Resolution display3840x2160p30Hz = new Resolution("3840 x 2160P 30Hz",
                            DisplayManager.DISPLAY_STANDARD_3840_2160P_30);
                    if (DisplayManager.DISPLAY_STANDARD_3840_2160P_30 == mCurrentStandardId){
                        display3840x2160p30Hz.setIschecked(true);
                    }
                    mResolutionList.add(display3840x2160p30Hz);
                    break;
                case DisplayManager.DISPLAY_STANDARD_3840_2160P_60:
                    Resolution display3840x2160p60Hz = new Resolution("3840 x 2160P 60Hz",
                            DisplayManager.DISPLAY_STANDARD_3840_2160P_60);
                    if (DisplayManager.DISPLAY_STANDARD_3840_2160P_60 == mCurrentStandardId){
                        display3840x2160p60Hz.setIschecked(true);
                    }
                    mResolutionList.add(display3840x2160p60Hz);
                    break;
                case DisplayManager.DISPLAY_STANDARD_4096_2160P_24:
                    Resolution display4096x2160p24Hz = new Resolution("4096 x 2160P 60Hz",
                            DisplayManager.DISPLAY_STANDARD_4096_2160P_24);
                    if (DisplayManager.DISPLAY_STANDARD_4096_2160P_24 == mCurrentStandardId){
                        display4096x2160p24Hz.setIschecked(true);
                    }
                    mResolutionList.add(display4096x2160p24Hz);
                    break;
                case DisplayManager.DISPLAY_STANDARD_4096_2160P_25:
                    Resolution display4096x2160p25Hz = new Resolution("4096 x 2160P 25Hz",
                            DisplayManager.DISPLAY_STANDARD_4096_2160P_25);
                    if (display4096x2160p25Hz.getId() == mCurrentStandardId){
                        display4096x2160p25Hz.setIschecked(true);
                    }
                    mResolutionList.add(display4096x2160p25Hz);
                    break;
                case DisplayManager. DISPLAY_STANDARD_4096_2160P_30:
                    Resolution display4096x2160p30Hz = new Resolution("4096 x 2160P 30Hz",
                            DisplayManager. DISPLAY_STANDARD_4096_2160P_30);
                    if (display4096x2160p30Hz.getId() == mCurrentStandardId){
                        display4096x2160p30Hz.setIschecked(true);
                    }
                    mResolutionList.add(display4096x2160p30Hz);
                    break;
                case DisplayManager.DISPLAY_STANDARD_4096_2160P_60:
                    Resolution display4096x2160p60Hz = new Resolution("4096 x 2160P 60Hz",
                            DisplayManager.DISPLAY_STANDARD_4096_2160P_60);
                    if (display4096x2160p60Hz.getId() == mCurrentStandardId){
                        display4096x2160p60Hz.setIschecked(true);
                    }
                    mResolutionList.add(display4096x2160p60Hz);
                    break;
                default:
                    break;



            }

        }

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        switch (requestCode){
            case RESOLUTION_ACTIVITY:
                if (RESULT_OK == resultCode){

                    mCurrentStandardId = data.getIntExtra("click_item_id", -1);
                    if (mCurrentStandardId == -1){
                        return;
                    }
                    Log.d(TAG, "onActivityResult: " + mCurrentStandardId);
                    Resolution currentResolution = mResolutionList.get(mCurrentStandardId-1);
                    currentResolution.setIschecked(true);
                    initResolutions();
                    mAdapter = new ResolutionAdapter(mResolutionList);
                    mRecyclerView.setAdapter(mAdapter);
                    //startThread();

                    displayManager.setDisplayStandard(mCurrentStandardId);
                }
                break;
            default:
                break;
        }
    }

   /*public void startThread(){

       new Thread(new Runnable() {
           @Override
           public void run() {


               initResolutions();
               mRecyclerView.setAdapter(mAdapter);
           }
       }).start();

   }*/





}

package com.example.eileen.boxsetting;

import android.content.Context;
import android.os.display.DisplayManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ResolutionActivity extends AppCompatActivity {
    private TextView tvMenuItem;
    private List<Resolution> resolutionList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resolution_activity);
        tvMenuItem = (TextView) findViewById(R.id.display);
        tvMenuItem.setBackgroundResource(R.drawable.menu_item_select);


    }

    public void initResolutions(){
        Context context = getApplicationContext();
        DisplayManager dm = (DisplayManager)context.getSystemService(
                Context.DISPLAY_MANAGER_SERVICE);
        int[] allSupportStandards = dm.getAllSupportStandards();

        for (int item : allSupportStandards) {
            if (item == DisplayManager.DISPLAY_STANDARD_1080P_60){
                Resolution display1080P60Hz = new Resolution("1080P 60Hz",
                        DisplayManager.DISPLAY_STANDARD_1080P_60);
                resolutionList.add(display1080P60Hz);
            }else if(item == DisplayManager.DISPLAY_STANDARD_1080P_50){
                Resolution display1080P50Hz = new Resolution("1080P 50Hz",
                        DisplayManager.DISPLAY_STANDARD_1080P_50);
                resolutionList.add(display1080P50Hz);
            }else if(item == DisplayManager.DISPLAY_STANDARD_1080P_30){
                Resolution display1080P30Hz = new Resolution("1080P 30Hz",
                        DisplayManager.DISPLAY_STANDARD_1080P_30);
                resolutionList.add(display1080P30Hz);
            }else if (item == DisplayManager.DISPLAY_STANDARD_1080P_25){
                Resolution display1080P25Hz = new Resolution("1080P 25Hz",
                        DisplayManager.DISPLAY_STANDARD_1080P_25);
                resolutionList.add(display1080P25Hz);
            }else if(item == DisplayManager.DISPLAY_STANDARD_1080P_24){
                Resolution display1080P24Hz = new Resolution("1080P 24Hz",
                        DisplayManager.DISPLAY_STANDARD_1080P_24);
                resolutionList.add(display1080P24Hz);
            }else if (item == DisplayManager.DISPLAY_STANDARD_1080I_60){
                Resolution display1080i60Hz = new Resolution("1080i 60Hz",
                        DisplayManager.DISPLAY_STANDARD_1080I_60);
                resolutionList.add(display1080i60Hz);
            }else if (item == DisplayManager.DISPLAY_STANDARD_1080I_50){
                Resolution display1080i50Hz = new Resolution("1080i 50Hz",
                        DisplayManager.DISPLAY_STANDARD_1080I_50);
                resolutionList.add(display1080i50Hz);
            }else if (item == DisplayManager.DISPLAY_STANDARD_720P_60){
                Resolution display720p60Hz = new Resolution("720P 60Hz",
                        DisplayManager.DISPLAY_STANDARD_720P_60);
                resolutionList.add(display720p60Hz);
            }else if (item == DisplayManager.DISPLAY_STANDARD_720P_50){
                Resolution display720p50Hz = new Resolution("720P 50Hz",
                        DisplayManager.DISPLAY_STANDARD_720P_50);
                resolutionList.add(display720p50Hz);
            }
        }

    }
}

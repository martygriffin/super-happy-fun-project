package com.superfunhappyproject.app;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.superfunhappyproject.app.util.WordLoaderService;

import sfh.com.superfunhappyproject.R;

/**
 * This is default activity, home to the menu of the app
 */
public class MainMenu extends Activity implements View.OnClickListener {
    final String TAG = "MainMenu";
    LinearLayout generateListButton;
    LinearLayout wifiButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        //loads words from words.csv to shared prefs
        WordLoaderService.loadWords(this);

        //init buttons
        generateListButton = (LinearLayout) findViewById(R.id.generate_list_button);
        wifiButton = (LinearLayout) findViewById(R.id.wifi_button);
        generateListButton.setOnClickListener(this);
        wifiButton.setOnClickListener(this);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        updateButtonSize();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.generate_list_button:
                startActivity(new Intent(this, GenerateListActivity.class));
                break;
            case R.id.wifi_button:
                startActivity(new Intent(this, WifiActivity.class));
                break;

        }
    }

    /**
     * changes button sizes via weight based on screen orientation
     */
    public void updateButtonSize() {

        //1 for portrait, 2 for landscape
        int rotation = getResources().getConfiguration().orientation;

        Log.d(TAG, "current rotation = " + rotation);

        LinearLayout.LayoutParams generateListLayoutParams = (LinearLayout.LayoutParams) generateListButton.getLayoutParams();
        LinearLayout.LayoutParams wifiLayoutParams = (LinearLayout.LayoutParams) wifiButton.getLayoutParams();

        if (rotation == 1) {
            generateListLayoutParams.weight = 1f;
            wifiLayoutParams.weight = 1f;
            generateListButton.setLayoutParams(generateListLayoutParams);
            wifiButton.setLayoutParams(wifiLayoutParams);

        } else {
            generateListLayoutParams.weight = 1.4f;
            wifiLayoutParams.weight = 1.4f;
            generateListButton.setLayoutParams(generateListLayoutParams);
            wifiButton.setLayoutParams(wifiLayoutParams);
        }

    }

}

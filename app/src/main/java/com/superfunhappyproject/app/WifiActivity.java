package com.superfunhappyproject.app;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import sfh.com.superfunhappyproject.R;


public class WifiActivity extends Activity {

    private ImageView wifiImage;
    private final String TAG="Wifi Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi);
        wifiImage = (ImageView) findViewById(R.id.wifi_status_image_view);
        updateWifiIcon(getWifiLevel());
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            updateWifiIcon(getWifiLevel());
        }
    };

    private int getWifiLevel() {
        WifiManager wm = (WifiManager) this.getSystemService(this.WIFI_SERVICE);
        int rssi = wm.getConnectionInfo().getRssi();
        int level = wm.calculateSignalLevel(rssi, 5);
        Log.d(TAG,"wifi level " + level);
        return level;
    }

    public void updateWifiIcon(int level) {
        switch (level) {
            case 0:
                wifiImage.setImageResource(R.mipmap.ic_actionbar_wifi_0);
                break;
            case 1:
                wifiImage.setImageResource(R.mipmap.ic_actionbar_wifi_1);
                break;
            case 2:
                wifiImage.setImageResource(R.mipmap.ic_actionbar_wifi_2);
                break;
            case 3:
                wifiImage.setImageResource(R.mipmap.ic_actionbar_wifi_3);
                break;
            case 4:
                wifiImage.setImageResource(R.mipmap.ic_actionbar_wifi_4);
                break;
            default:
                wifiImage.setImageResource(R.mipmap.ic_actionbar_wifi_disable);
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        //register for signal strength change updates
        this.registerReceiver(receiver, new IntentFilter(WifiManager.RSSI_CHANGED_ACTION));
    }

    @Override
    public void onPause() {
        super.onPause();
        this.unregisterReceiver(receiver);
    }

}

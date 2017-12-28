package com.android.jarvis;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import java.util.List;

/**
 * Created by mafei on 2017/12/26.
 */

public class Wifi extends AppCompatActivity {
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    registerReceiver(new BroadcastReceiver() {
      @Override
      public void onReceive(Context context, Intent intent) {
        scan_wifi();
      }
    }, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
  }

  private void scan_wifi() {
    //访问Wi-Fi Manager
    String service = Context.WIFI_SERVICE;
    final WifiManager wifi = (WifiManager) getSystemService(service);

    //监听并更改Wi-Fi状态
    if (!wifi.isWifiEnabled()) {
      if (wifi.getWifiState() != WifiManager.WIFI_STATE_ENABLING) {
        wifi.setWifiEnabled(true);
      }
    }

    //扫描wifi接入点，在开启了wifi，但还没有连上任何一个ssid时才会触发
    //注册用于接收扫描结果的broadcast receiver
    registerReceiver(new BroadcastReceiver() {
      @Override
      public void onReceive(Context context, Intent intent) {
        List<ScanResult> results = wifi.getScanResults();
        ScanResult bestSignal = null;
        for (ScanResult result : results) {
          if (bestSignal == null
              || WifiManager.compareSignalLevel(bestSignal.level, result.level) < 0) {
            bestSignal = result;
          }
        }
      }
    }, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));

    //开始扫描
    wifi.startScan();
  }

  @Override
  protected void onPostCreate(Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    moveTaskToBack(true);
  }

  @Override
  protected void onStop() {
    super.onStop();
    finish();
  }
}


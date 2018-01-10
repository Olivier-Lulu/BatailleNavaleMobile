package com.mobile.bataillenavale.lulu.bataillenavalemobile.controleur.communication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.p2p.WifiP2pConfig;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.util.Log;

import com.mobile.bataillenavale.lulu.bataillenavalemobile.R;
import com.mobile.bataillenavale.lulu.bataillenavalemobile.controleur.communication.MultijoueurActivity;
import com.mobile.bataillenavale.lulu.bataillenavalemobile.controleur.communication.WiFiDirectBroadcastReceiver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Cyril on 08/01/2018.
 */

public class MultijoueurServeurActivity extends MultijoueurActivity {
    private final IntentFilter intentFilter = new IntentFilter();
    WifiP2pManager.Channel mChannel;
    WifiP2pManager mManager;
    BroadcastReceiver mReceiver;
    private List<WifiP2pDevice> peers = new ArrayList<WifiP2pDevice>();
    WifiP2pDeviceList devicesList;
    WifiP2pDevice mDevice;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuserv);

        // Indicates a change in the Wi-Fi P2P status.
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);

        // Indicates a change in the list of available peers.
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);

        // Indicates the state of Wi-Fi P2P connectivity has changed.
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);

        // Indicates this device's details have changed.
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);

        mManager = (WifiP2pManager) getSystemService(Context.WIFI_P2P_SERVICE);
        mChannel = mManager.initialize(this, getMainLooper(), null);
        mReceiver = new WiFiDirectBroadcastReceiver(mManager, mChannel, this,devicesList);

    }

    @Override
    public void onResume() {
        super.onResume();
        registerReceiver(mReceiver, intentFilter);

        mManager.discoverPeers(mChannel, new WifiP2pManager.ActionListener() {
            @Override
            public void onSuccess() {
                Collection <WifiP2pDevice> devices= devicesList.getDeviceList();
                mDevice=devices.iterator().next();
                WifiP2pConfig config = new WifiP2pConfig();
                config.deviceAddress = mDevice.deviceAddress;
                mManager.connect(mChannel, config, new WifiP2pManager.ActionListener() {

                    @Override
                    public void onSuccess() {
                        //success logic
                    }

                    @Override
                    public void onFailure(int reason) {
                        //failure logic
                    }
                });
            }

            @Override
            public void onFailure(int reasonCode) {
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        unregisterReceiver(mReceiver);
    }



}

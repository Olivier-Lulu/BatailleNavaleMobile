package com.mobile.bataillenavale.lulu.bataillenavalemobile.controleur.communication;

import android.app.Activity;

/**
 * Created by Cyril on 10/01/2018.
 */

public abstract class MultijoueurActivity extends Activity {
    private boolean isWifiP2pEnabled = false;

    public void setIsWifiP2pEnabled(boolean isWifiP2pEnabled) {
        this.isWifiP2pEnabled = isWifiP2pEnabled;
    }


}

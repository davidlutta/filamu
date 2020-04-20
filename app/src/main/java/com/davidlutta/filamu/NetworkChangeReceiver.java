package com.davidlutta.filamu;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.davidlutta.filamu.util.ServiceManager;

public class NetworkChangeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        ServiceManager serviceManager = new ServiceManager(context);
        if (!serviceManager.isNetworkAvailable()) {
            Toast.makeText(context, "No Internet Connection", Toast.LENGTH_LONG).show();
        }
    }
}

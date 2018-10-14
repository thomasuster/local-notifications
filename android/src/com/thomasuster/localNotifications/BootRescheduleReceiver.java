package com.thomasuster.localNotifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootRescheduleReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        Intent serviceIntent = new Intent(context, RescheduleService.class);
        context.startService(serviceIntent);
    }

}
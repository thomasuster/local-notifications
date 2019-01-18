package com.thomasuster.localNotifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootRescheduleReceiver extends BroadcastReceiver {
    
    @Override
    public void onReceive(Context context, Intent intent) {
        Rescheduler rescheduler = new Rescheduler();
        rescheduler.run(context);
    }

}
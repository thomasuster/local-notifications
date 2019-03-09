package com.thomasuster.localNotifications;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.SystemClock;

public class LaunchReceiver extends BroadcastReceiver {

    Context context;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        PackageManager manager = context.getPackageManager();
        String packageName = intent.getStringExtra("packageName");
        Intent launchIntent = manager.getLaunchIntentForPackage(packageName);
        launchIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        delayLaunchSoNotificationCloses(context, launchIntent);
    }

    private void delayLaunchSoNotificationCloses(Context context, Intent launchIntent) {
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, launchIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        long ms = SystemClock.elapsedRealtime() + 300;
        AlarmManagerProxy alarmManager = new AlarmManagerProxy(context);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, ms, pendingIntent);
    }

}
package com.thomasuster.receivers;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.SystemClock;

public class Launch extends BroadcastReceiver {

    Context context;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        PackageManager manager = context.getPackageManager();
        String packageName = intent.getStringExtra("packageName");
        Intent launchIntent = manager.getLaunchIntentForPackage(packageName);
        launchIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        delayLaunchSoNotificationCloses(launchIntent);
    }

    private void delayLaunchSoNotificationCloses(Intent launchIntent) {
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, launchIntent, Intent.FLAG_ACTIVITY_NEW_TASK);
        long ms = SystemClock.elapsedRealtime() + 300;
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, ms, pendingIntent);
    }

}
package com.thomasuster.receivers;

import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.thomasuster.LocalNotifications;
import org.haxe.extension.Extension;
import android.os.SystemClock;
import android.content.pm.PackageManager;
import android.app.AlarmManager;

public class Launch extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        PackageManager manager = context.getPackageManager();
        String packageName = intent.getStringExtra("packageName");
        Intent launchIntent = manager.getLaunchIntentForPackage(packageName);
        launchIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, launchIntent, Intent.FLAG_ACTIVITY_NEW_TASK);

        long ms = SystemClock.elapsedRealtime() + 300;
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, ms, pendingIntent);
    }
}
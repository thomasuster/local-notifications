package com.thomasuster;

import com.thomasuster.receivers.Launch;
import org.haxe.extension.Extension;
import android.content.Context;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.app.AlarmManager;
import android.os.SystemClock;
import android.app.PendingIntent;
import android.app.Notification;
import com.thomasuster.R;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap;

public class LocalNotifications extends Extension {

    public LocalNotifications() {}

    public void onCreate(Bundle savedInstanceState) {
        System.out.println("HELLO WORLD!!!");
    }

    public static void schedule(int id, String title, String textContent, int ms) {
        System.out.println("schedule!!!");

        Intent intent = new Intent(mainContext, NotificationService.class);
        intent.putExtra("id", id);
        intent.putExtra("notification", makeNotification(title, textContent));

        PendingIntent pendingIntent = PendingIntent.getService(mainContext, id, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        long delay = SystemClock.elapsedRealtime() + ms;
        AlarmManager alarmManager = (AlarmManager) mainContext.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, delay, pendingIntent);
    }

    private static Notification makeNotification(String title, String textContent) {
        Intent intent = new Intent(mainContext, Launch.class);
        intent.putExtra("packageName", mainContext.getPackageName());
        PendingIntent pendingIntent = PendingIntent.getBroadcast(mainContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        long[] vibratePattern = {0, 1000};
        Bitmap large_icon = BitmapFactory.decodeResource(Extension.mainContext.getResources(), R.drawable.large_icon);
        Notification.Builder builder = new Notification.Builder(mainContext)
                .setSmallIcon(R.drawable.small_icon)
                .setLargeIcon(large_icon)
                .setContentTitle(title)
                .setContentText(textContent)
                .setTicker(textContent)
                .setAutoCancel(true)
                .setVibrate(vibratePattern)
                .setContentIntent(pendingIntent);
        return compress(builder.build());
    }

    private static Notification compress(Notification n) {
        n.largeIcon = null;
        return n;
    }

    public static void cancel(int id) {
        System.out.println("cancel!!!");
    }

}
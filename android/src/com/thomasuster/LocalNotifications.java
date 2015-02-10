package com.thomasuster;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import org.haxe.extension.Extension;

public class LocalNotifications extends Extension {

    public LocalNotifications() {}

    public void onCreate(Bundle savedInstanceState) {
    }

    public static void schedule(int id, String title, String textContent, int ms, int smallIconColor) {
        Intent intent = new Intent(mainContext, NotifyService.class);
        intent.putExtra("packageName", mainContext.getPackageName());
        intent.putExtra("title", title);
        intent.putExtra("textContent", textContent);
        intent.putExtra("smallIconColor", smallIconColor);

        PendingIntent pendingIntent = PendingIntent.getService(mainContext, id, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        long delay = SystemClock.elapsedRealtime() + ms;
        AlarmManager alarmManager = (AlarmManager) mainContext.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, delay, pendingIntent);
    }

    public static void cancel(int id) {
        Intent intent = new Intent(mainContext, NotifyService.class);
        PendingIntent pendingIntent = PendingIntent.getService(mainContext, id, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) mainContext.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);
    }

}
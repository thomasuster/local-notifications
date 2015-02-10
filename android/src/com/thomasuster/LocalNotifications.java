package com.thomasuster;

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
    }

    public static void schedule(int id, String title, String textContent, int ms) {
        Intent intent = new Intent(mainContext, NotifyService.class);
        intent.putExtra("id", id);
        intent.putExtra("packageName", mainContext.getPackageName());
        intent.putExtra("title", title);
        intent.putExtra("textContent", textContent);

        PendingIntent pendingIntent = PendingIntent.getService(mainContext, id, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        long delay = SystemClock.elapsedRealtime() + ms;
        AlarmManager alarmManager = (AlarmManager) mainContext.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, delay, pendingIntent);
    }

    public static void cancel(int id) {
        System.out.println("Implement cancel");
    }

}
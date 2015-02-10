package com.thomasuster;

import org.haxe.extension.Extension;
import android.content.Context;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.app.AlarmManager;
import android.os.SystemClock;
import android.app.PendingIntent;

public class LocalNotifications extends Extension {

    public LocalNotifications() {}

    public void onCreate(Bundle savedInstanceState) {
        System.out.println("HELLO WORLD!!!");
    }

    public static void schedule(int id, String title, String textContent, int ms) {
        System.out.println("schedule!!!");

        Intent intent = new Intent(mainContext, NotifyReceiver.class);
        intent.putExtra("id", id);
        intent.putExtra("title", title);
        intent.putExtra("textContent", textContent);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(mainContext, id, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        long delay = SystemClock.elapsedRealtime() + ms;
        AlarmManager alarmManager = (AlarmManager) mainContext.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, delay, pendingIntent);
    }

    public static void cancel(int id) {
        System.out.println("cancel!!!");
    }

}
package com.thomasuster.localNotifications;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import com.thomasuster.localNotifications.persistence.NotificationModel;
import com.thomasuster.localNotifications.persistence.NotificationVO;
import org.haxe.extension.Extension;

import java.util.Calendar;

public class LocalNotifications extends Extension {

    private static NotificationModel model;
    private static int[] ids;

    public LocalNotifications() {}

    public void onCreate(Bundle savedInstanceState) {
    }

    public static void schedule(int id, String title, String textContent, int ms, int smallIconColor) {
        NotificationVO vo = new NotificationVO();
        vo.id = id;
        vo.packageName = mainContext.getPackageName();
        vo.title = title;
        vo.textContent = textContent;
        vo.smallIconColor = smallIconColor;
        vo.ms = Calendar.getInstance().getTimeInMillis() + ms;
        Scheduler scheduler = new Scheduler();
        scheduler.schedule(mainContext, vo);
    }

    public static void cancelAll() {
        model = new NotificationModel(mainContext);
        makeIDs();
        model.removeAll();
        for (int i = 0; i < ids.length; i++)
            remove(ids[i]);
    }

    private static void makeIDs() {
        SQLiteDatabase db = model.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id FROM notifications", null);
        ids = new int[cursor.getCount()];
        int i = 0;
        while(cursor.moveToNext()) {
            ids[i] = cursor.getInt(0);
            i++;
        }
        db.close();
    }

    private static void remove(int id) {
        Intent intent = new Intent(mainContext, NotifyService.class);
        PendingIntent pendingIntent = PendingIntent.getService(mainContext, id, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) mainContext.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);
    }
}
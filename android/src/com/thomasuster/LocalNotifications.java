package com.thomasuster;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.thomasuster.persistence.NotificationModel;
import com.thomasuster.persistence.NotificationVO;
import org.haxe.extension.Extension;

import java.util.Calendar;

public class LocalNotifications extends Extension {

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

    public static void cancel(int id) {
        NotificationModel model = new NotificationModel(mainContext);
        model.remove(id);

        Intent intent = new Intent(mainContext, NotifyService.class);
        PendingIntent pendingIntent = PendingIntent.getService(mainContext, id, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) mainContext.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);
    }

}
package com.thomasuster;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import com.thomasuster.persistence.NotificationModel;
import com.thomasuster.persistence.NotificationVO;

import java.util.Calendar;

public class Scheduler {

    public Scheduler() {
    }

    public void schedule(Context context, NotificationVO vo) {
        NotificationModel model = new NotificationModel(context);
        model.add(vo);

        Intent intent = new Intent(context, NotifyService.class);
        intent.putExtra("id", vo.id);
        intent.putExtra("packageName", vo.packageName);
        intent.putExtra("title", vo.title);
        intent.putExtra("textContent", vo.textContent);
        intent.putExtra("smallIconColor", vo.smallIconColor);
        intent.putExtra("ms", vo.ms);
        PendingIntent pendingIntent = PendingIntent.getService(context, vo.id, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        long delayMS = vo.ms - Calendar.getInstance().getTimeInMillis();
        if(delayMS < 0)
            delayMS = 0;
        long delay = SystemClock.elapsedRealtime() + delayMS;
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setAndAllowWhileIdle(AlarmManager.ELAPSED_REALTIME_WAKEUP, delay, pendingIntent);
    }
}
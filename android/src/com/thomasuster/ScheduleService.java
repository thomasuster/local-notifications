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

public class ScheduleService extends IntentService {

    public ScheduleService() {
        super("ScheduleService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        NotificationVO vo = new NotificationVO();
        vo.id = intent.getIntExtra("id", 0);
        vo.packageName = intent.getStringExtra("packageName");
        vo.title = intent.getStringExtra("title");
        vo.textContent = intent.getStringExtra("textContent");
        vo.smallIconColor = intent.getIntExtra("smallIconColor",0);
        vo.ms = intent.getLongExtra("ms", 0);

        NotificationModel model = new NotificationModel(this);
        model.add(vo);

        intent.setClass(this, NotifyService.class);
        PendingIntent pendingIntent = PendingIntent.getService(this, vo.id, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        long delayMS = vo.ms - Calendar.getInstance().getTimeInMillis();
        if(delayMS < 0)
            delayMS = 0;
        long delay = SystemClock.elapsedRealtime() + delayMS;
        AlarmManager alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, delay, pendingIntent);
    }
}
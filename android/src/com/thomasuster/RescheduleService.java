package com.thomasuster;

import android.app.IntentService;
import android.content.Intent;
import android.database.Cursor;
import com.thomasuster.persistence.NotificationModel;
import com.thomasuster.persistence.NotificationVO;

import java.util.ArrayList;
import java.util.Calendar;

public class RescheduleService extends IntentService {

    private ArrayList<NotificationVO> vos;

    public RescheduleService() {
        super("RescheduleService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        makeVOs();
        removeAll();
        reschedule();
    }

    private void makeVOs() {
        vos = new ArrayList<NotificationVO>();
        NotificationModel model = new NotificationModel(this);
        Cursor cursor = model.getReadableDatabase().rawQuery("SELECT * FROM notifications", null);
        Calendar now = Calendar.getInstance();
        while(cursor.moveToNext()) {
            int index = cursor.getColumnIndex("ms");
            long ms = cursor.getLong(index);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(ms);
            System.out.println(ms);
            System.out.println(now.getTimeInMillis());
            if(calendar.after(now)) {
                NotificationVO vo = new NotificationVO();
                vo.id = cursor.getInt(0);
                vo.packageName = cursor.getString(1);
                vo.title = cursor.getString(2);
                vo.textContent = cursor.getString(3);
                vo.smallIconColor = cursor.getInt(4);
                vo.ms = ms;
                vos.add(vo);
                System.out.println("Notification is still valid.");
            }
            else {

                System.out.println("Notification to old.");
            }
        }
        model.close();
    }

    private void removeAll() {
        NotificationModel model = new NotificationModel(this);
        model.getWritableDatabase().execSQL("DELETE FROM notifications");
        model.close();
    }

    private void reschedule() {
        for (int i = 0; i < vos.size(); i++) {
            NotificationVO vo = vos.get(i);
            Intent serviceIntent = new Intent(this, ScheduleService.class);
            serviceIntent.putExtra("id", vo.id);
            serviceIntent.putExtra("packageName", vo.packageName);
            serviceIntent.putExtra("title", vo.title);
            serviceIntent.putExtra("textContent", vo.textContent);
            serviceIntent.putExtra("smallIconColor", vo.smallIconColor);
            serviceIntent.putExtra("ms", vo.ms);
            System.out.println("Schedule " + vo.id + " in " + vo.ms);
            startService(serviceIntent);
        }
    }
}
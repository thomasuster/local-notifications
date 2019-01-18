package com.thomasuster.localNotifications;

import android.app.IntentService;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.thomasuster.localNotifications.persistence.NotificationModel;
import com.thomasuster.localNotifications.persistence.NotificationVO;
import android.util.Log;
import java.util.ArrayList;
import java.util.Calendar;
import android.content.Context;

public class Rescheduler {

    private ArrayList<NotificationVO> vos;
    private Context context;
    
    public void run(Context context) {
        this.context = context;
        makeVOs();
        removeAll();
        reschedule();
    }

    private void makeVOs() {
        vos = new ArrayList<NotificationVO>();
        NotificationModel model = new NotificationModel(context);
        SQLiteDatabase db = model.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM notifications", null);
        Calendar now = Calendar.getInstance();
        while(cursor.moveToNext()) {
            int index = cursor.getColumnIndex("ms");
            long ms = cursor.getLong(index);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(ms);
            if(calendar.after(now)) {
                NotificationVO vo = new NotificationVO();
                vo.id = cursor.getInt(0);
                vo.packageName = cursor.getString(1);
                vo.title = cursor.getString(2);
                vo.textContent = cursor.getString(3);
                vo.smallIconColor = cursor.getInt(4);
                vo.ms = ms;
                vos.add(vo);
            }
        }
        db.close();
    }

    private void removeAll() {
        NotificationModel model = new NotificationModel(context);
        SQLiteDatabase db = model.getWritableDatabase();
        db.execSQL("DELETE FROM notifications");
        db.close();
    }

    private void reschedule() {
        Scheduler scheduler = new Scheduler();
        for (int i = 0; i < vos.size(); i++) {
            NotificationVO vo = vos.get(i);
            scheduler.schedule(context, vo);
        }
    }
}
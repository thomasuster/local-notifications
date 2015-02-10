package com.thomasuster;

import android.app.IntentService;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.thomasuster.receivers.Launch;

public class NotifyService extends IntentService {

    public NotifyService() {
        super("NotifyService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String packageName = intent.getStringExtra("packageName");
        String title = intent.getStringExtra("title");
        String textContent = intent.getStringExtra("textContent");

        Notification notification = makeNotification(packageName, title, textContent);
        NotificationManager notificationManager = (NotificationManager)this.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,notification);
    }

    private Notification makeNotification(String packageName, String title, String textContent) {
        Intent intent = new Intent(this, Launch.class);
        intent.putExtra("packageName", packageName);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        long[] vibratePattern = {0, 1000};
        Bitmap large_icon = BitmapFactory.decodeResource(this.getResources(), R.drawable.large_icon);
        Notification.Builder builder = new Notification.Builder(this)
                .setSmallIcon(R.drawable.small_icon)
                .setLargeIcon(large_icon)
                .setContentTitle(title)
                .setContentText(textContent)
                .setTicker(textContent)
                .setAutoCancel(true)
                .setVibrate(vibratePattern)
                .setContentIntent(pendingIntent);
        return builder.build();
    }
}
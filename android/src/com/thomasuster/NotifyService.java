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
import android.os.Build;
import com.thomasuster.persistence.NotificationModel;

public class NotifyService extends IntentService {

    int id;
    String packageName;
    String title;
    String textContent;
    int smallIconColor;

    public NotifyService() {
        super("NotifyService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        id = intent.getIntExtra("id", 0);
        packageName = intent.getStringExtra("packageName");
        title = intent.getStringExtra("title");
        textContent = intent.getStringExtra("textContent");
        smallIconColor = intent.getIntExtra("smallIconColor",0);

        NotificationModel model = new NotificationModel(this);
        model.remove(id);

        Notification notification = makeNotification();
        NotificationManager notificationManager = (NotificationManager)this.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,notification);

    }

    private Notification makeNotification() {
        Intent intent = new Intent(this, LaunchReceiver.class);
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
//                .setColor(smallIconColor) //API LEVEL 21
                .setVibrate(vibratePattern)
                .setContentIntent(pendingIntent);
//        return builder.build(); //API LEVEL 16
        return builder.getNotification();
    }
}
package com.thomasuster;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.app.Service;
import android.os.Looper;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.HandlerThread;
import android.os.Process;
import android.widget.Toast;
import android.app.IntentService;
import com.thomasuster.receivers.Launch;
import org.haxe.extension.Extension;
import org.haxe.lime.HaxeObject;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.app.Notification.Builder;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap;
import android.util.Log;
import android.content.pm.PackageManager;

public class NotifyService extends IntentService {

    public NotifyService() {
        super("NotifyService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        int id = intent.getIntExtra("id", 0);
        String packageName = intent.getStringExtra("packageName");
        String title = intent.getStringExtra("title");
        String textContent = intent.getStringExtra("textContent");

        Notification notification = makeNotification(packageName, title, textContent);
        NotificationManager notificationManager = (NotificationManager)this.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(id,notification);
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
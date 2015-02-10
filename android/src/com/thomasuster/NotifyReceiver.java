package com.thomasuster;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
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
import android.os.SystemClock;

public class NotifyReceiver extends BroadcastReceiver {

    Context context;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        int id = intent.getIntExtra("id", 0);
        String title = intent.getStringExtra("title");
        String textContent = intent.getStringExtra("textContent");
        notify(id, title, textContent);
    }

    private void notify(int id, String title, String textContent) {
        Intent intent = new Intent(context, NotifyReceiver.class);
        intent.setAction("launch");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        long[] vibratePattern = {0, 1000};
        Bitmap large_icon = BitmapFactory.decodeResource(Extension.mainContext.getResources(), R.drawable.large_icon);
        Notification.Builder builder = new Notification.Builder(context)
                .setSmallIcon(R.drawable.small_icon)
                .setLargeIcon(large_icon)
                .setContentTitle(title)
                .setContentText(textContent)
                .setTicker(textContent)
                .setAutoCancel(true)
                .setVibrate(vibratePattern)
                .setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(id, builder.build());
    }
}
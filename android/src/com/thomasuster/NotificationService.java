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

public class NotificationService extends IntentService {

    public NotificationService() {
        super("NotificationService");
        System.out.println("WAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        System.out.println("WAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        System.out.println("WAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        System.out.println("ASDAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        System.out.println("ASDAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        System.out.println("ASDAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        System.out.println("ASDAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        int id = intent.getIntExtra("id", 0);
        Notification notification = intent.getParcelableExtra("notification");
        Bitmap large_icon = BitmapFactory.decodeResource(this.getResources(), R.drawable.large_icon);
        notification.largeIcon = large_icon;
        NotificationManager notificationManager = (NotificationManager)this.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(id, notification);
    }
}
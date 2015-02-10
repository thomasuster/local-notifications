package com.thomasuster.receivers;

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
import com.thomasuster.R;

public class Notify extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        int id = intent.getIntExtra("id", 0);
        Notification notification = intent.getParcelableExtra("notification");
        Bitmap large_icon = BitmapFactory.decodeResource(Extension.mainContext.getResources(), R.drawable.large_icon);
        notification.largeIcon = large_icon;
        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(id, notification);
    }
}
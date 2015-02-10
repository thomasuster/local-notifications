package com.thomasuster.receivers;

import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import org.haxe.extension.Extension;

public class Launch extends BroadcastReceiver {

    Context context;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
//        String pac = intent.getStringExtra("title");
//        PackageManager manager = context.getPackageManager();
//        Intent i = manager.getLaunchIntentForPackage("org.openfl.samples.piratepig");
//        i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//        context.startActivity(i);


    }


}
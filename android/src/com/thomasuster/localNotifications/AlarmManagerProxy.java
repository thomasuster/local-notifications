package com.thomasuster.localNotifications;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.os.Build;
import android.os.PowerManager;
import android.content.Context;

public class AlarmManagerProxy {

    private final AlarmManager alarmManager;
    private final PowerManager powerManager;
    private final String packageName;

    public AlarmManagerProxy(Context context) {
        alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        powerManager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        packageName = context.getPackageName();
    }

    public void set(int type, long triggerAtMillis, PendingIntent operation) {
        if (isDozeSupported() && isDozeWhiteListed()) {
            alarmManager.setAndAllowWhileIdle(type, triggerAtMillis, operation);
        } else {
            alarmManager.set(type, triggerAtMillis, operation);
        }
    }

    private boolean isDozeSupported() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    private boolean isDozeWhiteListed() {
        return powerManager.isIgnoringBatteryOptimizations(packageName);
    }
}
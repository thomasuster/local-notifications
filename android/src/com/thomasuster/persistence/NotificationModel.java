package com.thomasuster.persistence;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NotificationModel extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String TABLE = "notifications";

    public NotificationModel(Context context) {
        super(context, "notifications", null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE + " (" +
                "id" + " INT, " +
                "packageName" + " TEXT, " +
                "title" + " TEXT, " +
                "textContent" + " TEXT, " +
                "smallIconColor" + " INT, " +
                "ms" + " INT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void add(NotificationVO vo) {
        String sqlString = "INSERT INTO notifications (id, packageName, title, textContent, smallIconColor, ms)  \n";
        sqlString += String.format("VALUES (%d,'%s','%s','%s',%d,%d)", vo.id, vo.packageName, vo.title, vo.textContent, vo.smallIconColor, vo.ms);
        getWritableDatabase().execSQL(sqlString);
        Cursor cursor = getReadableDatabase().query("notifications", null, null, null, null, null, null);
        System.out.println("COUNT IS: " + cursor.getCount());
        close();
    }

    public void remove(int id) {
        String sqlString = String.format("DELETE FROM notifications WHERE ID = %s", id);
        getWritableDatabase().execSQL(sqlString);
        close();
    }
}
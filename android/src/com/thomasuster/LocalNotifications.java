package com.thomasuster;

import org.haxe.extension.Extension;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class LocalNotifications extends Extension {

    public LocalNotifications() {}

    public void onCreate(Bundle savedInstanceState) {
        System.out.println("HELLO WORLD!!!");
    }

    public static void schedule(int id, String title, String textContent, int delay) {
        System.out.println("schedule!!!");
    }

    public static void cancel(int id) {
        System.out.println("cancel!!!");
    }

}
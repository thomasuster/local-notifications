package com.thomasuster;
interface LocalNotifications {
    function schedule(notification:Notification, seconds:Int):Void;
    function cancel(notification:Notification):Void;
}
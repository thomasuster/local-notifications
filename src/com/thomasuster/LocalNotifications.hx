package com.thomasuster;
interface LocalNotifications {
    function schedule(notification:Notification):Void;
    function cancel(id:Int):Void;
}
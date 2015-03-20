package com.thomasuster;
interface LocalNotifications {
    function schedule(notification:Notification):Void;
    function cancelAll():Void;
}
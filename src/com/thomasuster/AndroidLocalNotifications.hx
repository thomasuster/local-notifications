package com.thomasuster;

#if android
import openfl.utils.JNI;
#end

class AndroidLocalNotifications implements LocalNotifications {

    static var _schedule:Dynamic;
    static var _cancelAll:Dynamic;

    public function new():Void {}

    public function schedule(notification:Notification):Void {
        init();
        _schedule(notification.id, notification.title, notification.textContent, notification.milliseconds, notification.smallIconColor);
    }

    public function cancelAll():Void {
        init();
        _cancelAll();
    }

    function init():Void {
        if(_schedule == null) {
            #if android
            _schedule = JNI.createStaticMethod("com/thomasuster/LocalNotifications", "schedule", "(ILjava/lang/String;Ljava/lang/String;II)V");
            _cancelAll = JNI.createStaticMethod("com/thomasuster/LocalNotifications", "cancelAll", "()V");
            #end
        }
    }
}
package com.thomasuster;

import openfl.utils.JNI;

class AndroidLocalNotifications implements LocalNotifications {

    static var _schedule:Dynamic;
    static var _cancel:Dynamic;

    public function new():Void {}

    public function schedule(notification:Notification, seconds:Int):Void {
        init();
        _schedule(notification.id, notification.title, notification.textContent, seconds);
    }

    public function cancel(id:Int):Void {
        init();
        _cancel(id);
    }

    function init():Void {
        if(_schedule == null) {
            _schedule = JNI.createStaticMethod("com/thomasuster/LocalNotifications", "schedule", "(ILjava/lang/String;Ljava/lang/String;I)V");
            _cancel = JNI.createStaticMethod("com/thomasuster/LocalNotifications", "cancel", "(I)V");
        }
    }
}
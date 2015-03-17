package com.thomasuster;
import cpp.Lib;
class IOSLocalNotifications implements LocalNotifications {

    static var _init_ios:Dynamic;
    static var _schedule:Dynamic;
    static var _cancel:Dynamic;

    public function new():Void {}

    public function schedule(notification:Notification) {
        init();
        _schedule(notification.id, notification.textContent, Math.round(notification.milliseconds/1000));
    }

    public function cancel(id:Int){
        init();
        _cancel(id);
    }

    function init():Void {
        if(_init_ios == null) {
            #if ios
            _init_ios = Lib.load("localnotification","init_ios",0);
            _schedule = Lib.load("localnotification","schedule",3);
            _cancel = Lib.load("localnotification","cancel",1);
            #end
            _init_ios();
        }
    }
}
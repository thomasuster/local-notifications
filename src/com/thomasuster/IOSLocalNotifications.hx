package com.thomasuster;
import cpp.Lib;
class IOSLocalNotifications implements InitRequiredLocalNotifications {

    static var _init_ios:Dynamic;
    static var _schedule:Dynamic;
    static var _cancelAll:Dynamic;
    static var _isAllowed:Dynamic;

    public function new():Void {}

    public function schedule(notification:Notification) {
        init();
        _schedule(notification.id, notification.textContent, Math.round(notification.milliseconds/1000));
    }

    public function cancelAll(){
        init();
        _cancelAll();
    }

    public function init():Void {
        if(_init_ios == null) {
            #if ios
            _init_ios = Lib.load("localnotification","init_ios",0);
            _schedule = Lib.load("localnotification","schedule",3);
            _cancelAll = Lib.load("localnotification","cancelAll",0);
            #end
            _init_ios();
        }
    }

    public function isAllowed():Bool {
        if(_isAllowed == null)
            _isAllowed = Lib.load("localnotification","isAllowed",0);
        if(_isAllowed() == 0)
            return false;
        return true;
    }
}
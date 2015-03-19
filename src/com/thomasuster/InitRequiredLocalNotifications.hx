package com.thomasuster;
interface InitRequiredLocalNotifications extends LocalNotifications {
    function init():Void;
    function isAllowed():Bool;
}
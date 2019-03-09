package ;
import com.thomasuster.Notification;
import com.thomasuster.AndroidLocalNotifications;
import nme.display.Bitmap;
import nme.display.Sprite;
import nme.Assets;
import nme.Lib;
import flash.events.Event;

class Main extends Sprite {
    
    public function new () {
        
        super ();

        scheduleNotification();        

        addEventListener(Event.ACTIVATE, onActivate);
        
        var bitmap = new Bitmap (Assets.getBitmapData ("assets/nme.png"));
        addChild (bitmap);
        
        bitmap.x = (Lib.current.stage.stageWidth - bitmap.width) / 2;
        bitmap.y = (Lib.current.stage.stageHeight - bitmap.height) / 2;
        
    }
    
    function scheduleNotification():Void {
        var android:AndroidLocalNotifications = new AndroidLocalNotifications();
        var notification = new Notification();
        notification.title = 'Pakka Phone';
        notification.textContent = "Some content!";
        notification.milliseconds = 5 * 1000;
        notification.smallIconColor = 0xff3c81;
        notification.id = 1337;
        android.schedule(notification);
    }

    function onActivate(event:Event):Void {
        scheduleNotification();
    }
}
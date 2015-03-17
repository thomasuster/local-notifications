#import <Foundation/Foundation.h>
#import <UIKit/UILocalNotification.h>
#import <UIKit/UIApplication.h>
#import <UIKit/UIUserNotificationSettings.h>
#import <UIKit/UIKit.h>

@interface LocalNotifications:NSObject
@end

@interface NMEAppDelegate:NSObject <UIApplicationDelegate>
@end

@interface NMEAppDelegate (LocalNotifications)
    @property(nonatomic,retain) id localNotif;
@end

static char const * const LocalNotifKey="local-notifications";

namespace localnotifications {
    UIApplication *application;
    UIUserNotificationSettings *settings;

    void _init_ios() {
        application=[UIApplication sharedApplication];
        // The following line must only run under iOS 8. This runtime check prevents
        // it from running if it doesn't exist (such as running under iOS 7 or earlier).
        float version=[[[UIDevice currentDevice] systemVersion] floatValue];
        if (version >= 8.0){
            settings=[UIUserNotificationSettings settingsForTypes:(UIUserNotificationTypeBadge|UIUserNotificationTypeAlert|UIUserNotificationTypeSound) categories:nil];
            [application registerUserNotificationSettings:settings];
            [application registerForRemoteNotifications];
        }
    }

    void _schedule(int id, const char* body, int sec) {
        NSLog(@"Sec: %d", sec);

        NSString* alertBody=[[NSString alloc] initWithUTF8String:body];

        UILocalNotification *notification = [[UILocalNotification alloc] init];
        notification.alertBody = alertBody;
        notification.fireDate = [[NSDate date] dateByAddingTimeInterval:sec];
        notification.soundName=UILocalNotificationDefaultSoundName;

        NSDictionary *userDict = [NSDictionary dictionaryWithObjectsAndKeys:
                                             [NSNumber numberWithInt:id],@"id",
                                             nil];
        notification.userInfo = userDict;

        NSLog(@"notification_add");
        [[UIApplication sharedApplication] scheduleLocalNotification:notification];

        [notification release];
    }

    void _cancel(int id) {
        NSLog(@"cancel: %d", id);
        UIApplication *app = [UIApplication sharedApplication];
        NSArray *eventArray = [app scheduledLocalNotifications];
        NSLog(@"count: %d", [eventArray count]);
        for (int i=0; i<[eventArray count]; i++) {
            UILocalNotification* oneEvent = [eventArray objectAtIndex:i];
            NSDictionary *userInfoCurrent = oneEvent.userInfo;
            int _id = [userInfoCurrent[@"id"] intValue];
            NSLog(@"_id: %d", _id);
            if (_id == id) {
                NSLog(@"canceled! %d", id);
                //Cancelling local notification
                [app cancelLocalNotification:oneEvent];
                break;
            }
        }
    }
}






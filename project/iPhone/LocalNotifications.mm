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

    // The following line must only run under iOS 8. This runtime check prevents
    // it from running if it doesn't exist (such as running under iOS 7 or earlier).
    bool isNewEnough() {
        float version=[[[UIDevice currentDevice] systemVersion] floatValue];
        return version >= 8.0;
    }


    void _init_ios() {
        if (isNewEnough()){
            application=[UIApplication sharedApplication];
            settings=[UIUserNotificationSettings settingsForTypes:(UIUserNotificationTypeBadge|UIUserNotificationTypeAlert|UIUserNotificationTypeSound) categories:nil];
            [application registerUserNotificationSettings:settings];
            [application registerForRemoteNotifications];
        }
    }

    int _isAllowed() {
        if(!isNewEnough())
            return 1;
        UIUserNotificationSettings *grantedSettings = [[UIApplication sharedApplication] currentUserNotificationSettings];
        if(grantedSettings.types == UIUserNotificationTypeNone)
            return 0;
        return 1;
    }

    void _schedule(int id, const char* body, int sec) {
        UILocalNotification *notification = [[UILocalNotification alloc] init];
        notification.alertBody = [[NSString alloc] initWithUTF8String:body];
        notification.fireDate = [[NSDate date] dateByAddingTimeInterval:sec];

        if(isNewEnough()) {
            UIUserNotificationSettings *grantedSettings = [[UIApplication sharedApplication] currentUserNotificationSettings];
            if (grantedSettings.types & UIUserNotificationTypeSound)
                notification.soundName=UILocalNotificationDefaultSoundName;
        }

        NSDictionary *userDict = [NSDictionary dictionaryWithObjectsAndKeys:
                                             [NSNumber numberWithInt:id],@"id",
                                             nil];
        notification.userInfo = userDict;

        [[UIApplication sharedApplication] scheduleLocalNotification:notification];

        [notification release];
    }

    void _cancelAll() {
        [[UIApplication sharedApplication] cancelAllLocalNotifications];
    }
}






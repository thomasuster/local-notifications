# local-notifications (Private Repo)

OpenFL extension for Android Local Notifications

### Getting Started

1. Fork your own copy of thomasuster/local-notifications
1. Clone your copy
  ```
	git clone git@github.com:thomasuster/local-notifications.git
  ```
1. Add this to your project.xml

  ```
<include path="../local-notifications/include.xml" />
  ```
  
1. Add this this to your project
  ```
	var localNotifications:AndroidLocalNotifications = new AndroidLocalNotifications();
	var notification:Notification = new Notification();
	notification.id = 1;
	notification.title = "Title";
	notification.textContent = "This is the text content.";
	notification.milliseconds = 5*1000;
	notification.smallIconColor = 0xff3c81;
	localNotifications.schedule(notification);
	//localNotifications.cancel(1);
  ```
1. 
  ```
  openfl test android
  ```
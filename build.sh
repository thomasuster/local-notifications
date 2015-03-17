rm ndll/iPhone/*
lime rebuild local-notifications ios
pushd ../testLocalNotification
openfl test ios
popd
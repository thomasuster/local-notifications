package com;
class JNIFunctionCache {

    public var libraryPath:String;

    var map:StringMap<Dynamic>;

    public function new(libraryPath:String):Void {
        map = new StringMap();
        libraryPath = libraryPath;
    }

    public function cacheFunc(funcName:String, params:String) {
        map.set(funcName, JNI.createStaticMethod(libraryPath, funcName, params));
    }

    public function getFunc(funcName:String) {
        return map.get(funcName);
    }
}
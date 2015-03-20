#ifndef STATIC_LINK
#define IMPLEMENT_API
#endif
#include <hx/CFFI.h>
#include "Utils.h"

using namespace localnotifications;

void init_ios(){
    _init_ios();
}
DEFINE_PRIM (init_ios,0);

static value isAllowed(){
	int returnValue = _isAllowed();
    return alloc_int(returnValue);
}
DEFINE_PRIM (isAllowed,0);

void schedule(value id, value body, value sec){
    int _id=val_int(id);
    const char* _body=val_get_string(body);
    int _sec=val_int(sec);
    _schedule(_id,_body,_sec);
}

DEFINE_PRIM (schedule, 3);

void cancelAll(){
    _cancelAll();
}

DEFINE_PRIM (cancelAll, 0);

extern "C" void localnotifications_main () {

}
DEFINE_ENTRY_POINT (localnotifications_main);

extern "C" int localnotifications_register_prims () { return 0; }
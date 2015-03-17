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

void schedule(value id, value body, value sec){
    int _id=val_int(id);
    const char* _body=val_get_string(body);
    int _sec=val_int(sec);
    _schedule(_id,_body,_sec);
}

DEFINE_PRIM (schedule, 3);

void cancel(value id){
    int cInt=val_int(id);
    _cancel(cInt);
}

DEFINE_PRIM (cancel, 1);

extern "C" void localnotifications_main () {

}
DEFINE_ENTRY_POINT (localnotifications_main);

extern "C" int localnotifications_register_prims () { return 0; }
#ifndef LOCALNOTIFICATIONS_H
#define LOCALNOTIFICATIONS_H

namespace localnotifications {

	void _init_ios();
	void _schedule(int id, const char* body, int sec);
	void _cancel(int id);

}

#endif
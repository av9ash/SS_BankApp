package org.asu.cse545.group4.server.eventservice.service;

import org.asu.cse545.group4.server.sharedobjects.TblEventLog;

public abstract interface EventService {

	void logEvent(TblEventLog eventLog);
}

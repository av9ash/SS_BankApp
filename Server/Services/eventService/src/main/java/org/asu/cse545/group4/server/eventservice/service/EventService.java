package org.asu.cse545.group4.server.eventservice.service;

import java.util.List;
import java.util.Map;

import org.asu.cse545.group4.server.sharedobjects.TblEventLog;
import org.asu.cse545.group4.server.sharedobjects.TblUser;

public abstract interface EventService {

	void logEvent(TblEventLog eventLog);
	List<TblEventLog> searchEvents();

}

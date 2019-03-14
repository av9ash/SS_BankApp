package org.asu.cse545.group4.server.eventservice.dao;

import org.asu.cse545.group4.server.sharedobjects.TblEventLog;

public abstract interface EventDAO {

	void logEvent(TblEventLog eventLog);
}

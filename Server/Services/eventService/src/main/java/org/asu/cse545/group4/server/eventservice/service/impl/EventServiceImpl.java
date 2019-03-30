package org.asu.cse545.group4.server.eventservice.service.impl;

import java.util.List;
import java.util.Map;

import org.asu.cse545.group4.server.eventservice.dao.EventDAO;
import org.asu.cse545.group4.server.eventservice.service.EventService;
import org.asu.cse545.group4.server.sharedobjects.TblEventLog;
import org.asu.cse545.group4.server.sharedobjects.TblUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("EventService")
public class EventServiceImpl implements EventService {

	@Autowired
	private EventDAO eventDAO;
	
	@Transactional
	public void logEvent(TblEventLog eventLog) {
		// TODO Auto-generated method stub
		eventDAO.logEvent(eventLog);

	}
	@Transactional
	  public List<TblEventLog> searchEvents()
	  {
	  	return eventDAO.searchEvents();
	  }

}

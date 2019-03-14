package org.asu.cse545.group4.server.eventservice.dao.impl;

import java.util.Date;

import org.asu.cse545.group4.server.eventservice.dao.EventDAO;
import org.asu.cse545.group4.server.sharedobjects.TblEventLog;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EventDAOImpl implements EventDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void logEvent(TblEventLog eventLog) {
		// TODO Auto-generated method stub
		eventLog.setCreatedDate(new Date());
		this.sessionFactory.getCurrentSession().save(eventLog);
	}

}

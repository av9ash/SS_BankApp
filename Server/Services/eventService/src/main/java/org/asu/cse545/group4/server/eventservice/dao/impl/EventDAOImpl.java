package org.asu.cse545.group4.server.eventservice.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.asu.cse545.group4.server.eventservice.dao.EventDAO;
import org.asu.cse545.group4.server.sharedobjects.TblEventLog;
import org.asu.cse545.group4.server.sharedobjects.TblUser;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
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
	public List<TblEventLog> searchEvents()
	{
		
		final CriteriaBuilder builder = sessionFactory.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<TblEventLog> criteriaQuery = builder.createQuery(TblEventLog.class);
        Root<TblEventLog> userQuery = criteriaQuery.from(TblEventLog.class);
    
        
        Query<TblEventLog> query = sessionFactory.getCurrentSession().createQuery(criteriaQuery);
        final List<TblEventLog> results = query.getResultList();
        
		return results;
	}

}

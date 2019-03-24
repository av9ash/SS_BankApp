package org.asu.cse545.group4.server.appointmentService.dao.impl;

import java.util.*;
import java.util.Date;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.CriteriaQuery;


import org.asu.cse545.group4.server.appointmentService.dao.AppointmentDAO;
import org.asu.cse545.group4.server.sharedobjects.TblAppointment;
import org.asu.cse545.group4.server.sharedobjects.TblUser;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class AppointmentDAOImpl implements AppointmentDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public void insertAppointment(TblAppointment paramTblAppointment) {
		Session session = this.sessionFactory.getCurrentSession();
		Date date = new Date();
		paramTblAppointment.setCreatedDate(date);
		//paramTblAppointment.setAppointmentStatus();
		this.sessionFactory.getCurrentSession().save(paramTblAppointment);
		
		TblUser user = paramTblAppointment.getTblUserByAppointmentUserId();
		Set<TblAppointment> tblAppointmentsForAppointmentUserId =  user.getTblAppointmentsForAppointmentUserId();
		tblAppointmentsForAppointmentUserId.add(paramTblAppointment);
		user.setTblAppointmentsForAppointmentUserId(tblAppointmentsForAppointmentUserId);
		
		user = paramTblAppointment.getTblUserByAssignedToUserId();
		Set<TblAppointment> tblAppointmentsForAssignedToUserId =  user.getTblAppointmentsForAssignedToUserId();
		tblAppointmentsForAssignedToUserId.add(paramTblAppointment);
		user.setTblAppointmentsForAssignedToUserId(tblAppointmentsForAssignedToUserId);
		
		session.saveOrUpdate(user);

	}

	@Override
	public List<TblAppointment> searchAppointments(TblAppointment paramTblAppointment) {
		
		try
		{/*
			final CriteriaBuilder builder = sessionFactory.getCurrentSession().getCriteriaBuilder();
	        CriteriaQuery<TblAppointment> criteriaQuery = builder.createQuery(TblAppointment.class);
	        Root<TblAppointment> userQuery = criteriaQuery.from(TblAppointment.class);      
	        List<TblAppointment> appointments = new ArrayList<TblAppointment>();
	        if(paramTblAppointment.getTblUserByAssignedToUserId() != null)
	       {
	        	TblUser user = paramTblAppointment.getTblUserByAssignedToUserId();
	    		Set<TblAppointment> tblAppointmentsForAssignedToUserId =  user.getTblAppointmentsForAssignedToUserId();
	    		appointments.addAll(tblAppointmentsForAssignedToUserId);
	       }        
	        
	      
	       Query<TblAppointment> query = sessionFactory.getCurrentSession().createQuery(criteriaQuery);
	       final List<TblUserProfile> results = query.getResultList();
	        if(!results.isEmpty())
	       {
	           TblUserProfile returnedUser = results.get(0);
	           return returnedUser;
	       }
	       else
	       {
	           return null;
	       }
	       */
			return null;
		}
		catch(Exception e)
		{
			return null;
		}
	}
	
	
}

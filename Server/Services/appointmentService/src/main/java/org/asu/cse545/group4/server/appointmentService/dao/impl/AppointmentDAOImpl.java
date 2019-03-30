package org.asu.cse545.group4.server.appointmentService.dao.impl;

import java.util.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.CriteriaQuery;


import org.asu.cse545.group4.server.appointmentService.dao.AppointmentDAO;
import org.asu.cse545.group4.server.sharedobjects.TblAppointment;
import org.asu.cse545.group4.server.sharedobjects.TblUser;
import org.asu.cse545.group4.server.sharedobjects.AppointmentUserInfo;
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
		
		
		
		session.saveOrUpdate(user);

	}

	@Override
	public List<AppointmentUserInfo> searchAppointments(TblUser paramTblUser) {
		
		final CriteriaBuilder builder = sessionFactory.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<TblAppointment> criteriaQuery = builder.createQuery(TblAppointment.class);
        Root<TblAppointment> userQuery = criteriaQuery.from(TblAppointment.class);
        //TblUser user = this.sessionFactory.getCurrentSession().get(TblUser.class , paramTblUser.getUserId());
		try
		{
        	criteriaQuery.where(builder.equal(userQuery.get("tblUserByAssignedToUserId").<Integer>get("userId"), paramTblUser.getUserId()));
        	//criteriaQuery.where(builder.equal(rootObj.get("Y").<String> get("Z"), param1));}
        	Query<TblAppointment> query = sessionFactory.getCurrentSession().createQuery(criteriaQuery);
            final List<TblAppointment> results = query.getResultList();
            AppointmentUserInfo appointmentInfoObj;
            List<AppointmentUserInfo> finalResult = new ArrayList<AppointmentUserInfo>();
            
            for(int i=0; i < results.size(); i++) {
            	TblUser userObj = results.get(i).getTblUserByAppointmentUserId();
            	String fname = userObj.getTblUserProfile().getFirstName();
            	String lname = userObj.getTblUserProfile().getLastName();
            	String email = userObj.getTblUserProfile().getEmail();
            	String dateCreated = results.get(i).getCreatedDate().toString();
            	String descript = results.get(i).getDescript();

            	appointmentInfoObj = new AppointmentUserInfo(fname, lname, email, dateCreated, descript);
            	finalResult.add(appointmentInfoObj);
            }
            
            return finalResult;
			//return "";
		}
		catch(Exception e)
		{
			return null;
			//return e.getMessage();
		}
	}
	
	  public List<TblUser>  getEmployees() {
		  final CriteriaBuilder builder = sessionFactory.getCurrentSession().getCriteriaBuilder();
			CriteriaQuery<TblUser> criteriaQuery = builder.createQuery(TblUser.class);
	        Root<TblUser> userQuery = criteriaQuery.from(TblUser.class);

	        criteriaQuery.where(builder.lessThan(userQuery.get("isExternalUser"), 3));
        	Query<TblUser> query = sessionFactory.getCurrentSession().createQuery(criteriaQuery);
            final List<TblUser> results = query.getResultList();
            
            return results;
            
	        
	  }

	
	
}

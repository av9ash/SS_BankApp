package org.asu.cse545.group4.server.appointmentService.dao.impl;

import java.util.Date;
import org.asu.cse545.group4.server.appointmentService.dao.AppointmentDAO;
import org.asu.cse545.group4.server.sharedobjects.TblAppointment;
import org.hibernate.Session;
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
		paramTblAppointment.setAppointmentStatus(1);
		this.sessionFactory.getCurrentSession().save(paramTblAppointment);

	}
}

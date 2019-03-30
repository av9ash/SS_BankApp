package org.asu.cse545.group4.server.appointmentService.service.impl;

import java.util.*;
import org.asu.cse545.group4.server.appointmentService.dao.AppointmentDAO;
import org.asu.cse545.group4.server.appointmentService.service.AppointmentService;
import org.asu.cse545.group4.server.sharedobjects.TblAppointment;
import org.asu.cse545.group4.server.sharedobjects.TblUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.asu.cse545.group4.server.sharedobjects.AppointmentUserInfo;


@Service("appointmentService")
public class AppointmentServiceImpl implements AppointmentService {
	@Autowired
	private AppointmentDAO appointmentDAO;

	@Transactional
	public void insertAppointment(TblAppointment paramTblAppointment) {
		 this.appointmentDAO.insertAppointment(paramTblAppointment);
	}
	
	@Transactional
	public List<AppointmentUserInfo>  searchAppointments(TblUser paramTblUser)
	{
		return this.appointmentDAO.searchAppointments(paramTblUser);
	}
	
	@Transactional
	 public List<TblUser> getEmployees() {
		return this.appointmentDAO.getEmployees();
	}

	
}
